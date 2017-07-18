package com.mx.visolutions.sae.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mx.visolutions.sae.dto.BecaForm;
import com.mx.visolutions.sae.entities.Alumno;
import com.mx.visolutions.sae.entities.AlumnoBeca;
import com.mx.visolutions.sae.entities.AlumnoPago;
import com.mx.visolutions.sae.repositories.AlumnoBecaRepository;
import com.mx.visolutions.sae.repositories.AlumnoPagoRepository;
import com.mx.visolutions.sae.repositories.AlumnoRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class AlumnoBecaServiceImpl implements AlumnoBecaService{
	
	
	private AlumnoBecaRepository alumnoBecaRepository;
	private AlumnoRepository alumnoRepository;
	private AlumnoPagoRepository alumnoPagoRepository;
	
	@Autowired
	public AlumnoBecaServiceImpl(AlumnoBecaRepository alumnoBecaRepository, AlumnoRepository alumnoRepository,
			AlumnoPagoRepository alumnoPagoRepository){
		this.alumnoBecaRepository = alumnoBecaRepository;
		this.alumnoRepository = alumnoRepository;
		this.alumnoPagoRepository = alumnoPagoRepository;
	}

	@Override
	public void save(BecaForm becaForm, Integer idAlumno) {
		AlumnoBeca alumnoBeca = new AlumnoBeca();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		double diferencia = 0.0;
		double montoPorcentaje;
		
		try {
			alumnoBeca.setFechaFin(formatter.parse(becaForm.getFechaFin()));
			alumnoBeca.setFechaInicio(formatter.parse(becaForm.getFechaInicio()));
			alumnoBeca.setPorcentaje(becaForm.getPorcentaje());
			alumnoBeca.setIdAlumno(idAlumno);
			
			//Guardar la beca
			alumnoBecaRepository.save(alumnoBeca);
			
			//Obtener los pagos donde se aplica la beca
			List<AlumnoPago> lstPagos = alumnoBecaRepository.findPagosAplicaBeca(idAlumno, alumnoBeca.getFechaInicio(), alumnoBeca.getFechaFin());
			
			//Modificar los pagos y sumar la diferencia de los pagos
			for(AlumnoPago pago : lstPagos){
				montoPorcentaje = (pago.getMonto() * becaForm.getPorcentaje())/100;
				pago.setMonto(pago.getMonto()-montoPorcentaje);
				
				if(pago.getPago()>pago.getMonto()){
					diferencia = diferencia + (pago.getPago()-pago.getMonto());
				}
				
				alumnoPagoRepository.save(pago);
			}
			
			//Establecer la diferencia en el alumno
			Alumno alumno = alumnoRepository.findOne(idAlumno);
			alumno.setSaldo(alumno.getSaldo()+diferencia);
			alumnoRepository.save(alumno);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public BecaForm findOne(Integer idBeca) {
		BecaForm becaForm = new BecaForm();
		AlumnoBeca alumnoBeca;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		alumnoBeca = alumnoBecaRepository.findOne(idBeca);
		
		becaForm.setBecaId(alumnoBeca.getIdBeca());
		becaForm.setPorcentaje(alumnoBeca.getPorcentaje());
		becaForm.setFechaInicio(formatter.format(alumnoBeca.getFechaInicio()));
		becaForm.setFechaFin(formatter.format(alumnoBeca.getFechaFin()));
		
		return becaForm;
	}

	@Override
	public List<BecaForm> findListAlumno(Integer idAlumno) {
		List<BecaForm> lst = null;
		List<AlumnoBeca> lstAlumno;
		BecaForm form;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		lstAlumno = alumnoBecaRepository.findByIdAlumno(idAlumno);
		
		if(lstAlumno != null){
			lst = new ArrayList<BecaForm>();
			for(AlumnoBeca alumno : lstAlumno){
				form = new BecaForm();
				form.setBecaId(alumno.getIdBeca());
				form.setPorcentaje(alumno.getPorcentaje());
				form.setFechaInicio(formatter.format(alumno.getFechaInicio()));
				form.setFechaFin(formatter.format(alumno.getFechaFin()));
				lst.add(form);
			}
		}
		return lst;
	}

	@Override
	public BecaForm findByAlumnoAndCurrentDate(Integer idAlumno) {

		AlumnoBeca alumnoBeca;
		BecaForm becaForm = null;
		SimpleDateFormat formatter = null;
		
		alumnoBeca = alumnoBecaRepository.findByIdAlumnoAndCurrentDate(idAlumno);
		if(alumnoBeca!=null){
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			becaForm = new BecaForm();
			becaForm.setBecaId(alumnoBeca.getIdBeca());
			becaForm.setPorcentaje(alumnoBeca.getPorcentaje());
			becaForm.setFechaInicio(formatter.format(alumnoBeca.getFechaInicio()));
			becaForm.setFechaFin(formatter.format(alumnoBeca.getFechaFin()));
		}
		
		return becaForm;
	}

	@Override
	public BecaForm findByAlumnoAndDate(Integer idAlumno, Date fechaInicio) {
		AlumnoBeca alumnoBeca;
		BecaForm becaForm = null;
		SimpleDateFormat formatter = null;
		
		alumnoBeca = alumnoBecaRepository.findByIdAlumnoAndDate(idAlumno, fechaInicio);
		if(alumnoBeca!=null){
			formatter = new SimpleDateFormat("dd-MM-yyyy");
			becaForm = new BecaForm();
			becaForm.setBecaId(alumnoBeca.getIdBeca());
			becaForm.setPorcentaje(alumnoBeca.getPorcentaje());
			becaForm.setFechaInicio(formatter.format(alumnoBeca.getFechaInicio()));
			becaForm.setFechaFin(formatter.format(alumnoBeca.getFechaFin()));
		}
		
		return becaForm;
	}

}
