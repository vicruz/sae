package com.mx.visolutions.sae.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mx.visolutions.sae.dto.DescuentoForm;
import com.mx.visolutions.sae.entities.Alumno;
import com.mx.visolutions.sae.entities.AlumnoDescuento;
import com.mx.visolutions.sae.entities.AlumnoPago;
import com.mx.visolutions.sae.repositories.AlumnoBecaRepository;
import com.mx.visolutions.sae.repositories.AlumnoDescuentoRepository;
import com.mx.visolutions.sae.repositories.AlumnoPagoRepository;
import com.mx.visolutions.sae.repositories.AlumnoRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class AlumnoDescuentoServiceImpl implements AlumnoDescuentoService{

	private AlumnoDescuentoRepository alumnoDescuentoRepository;
	private AlumnoPagoRepository alumnoPagoRepository;
	private AlumnoBecaRepository alumnoBecaRepository;
	private AlumnoRepository alumnoRepository;
	
	@Autowired
	public AlumnoDescuentoServiceImpl(AlumnoDescuentoRepository alumnoDescuentoRepository,
			AlumnoPagoRepository alumnoPagoRepository, AlumnoBecaRepository alumnoBecaRepository,
			AlumnoRepository alumnoRepository){
		this.alumnoDescuentoRepository = alumnoDescuentoRepository;
		this.alumnoPagoRepository = alumnoPagoRepository;
		this.alumnoBecaRepository = alumnoBecaRepository;
		this.alumnoRepository = alumnoRepository;
	}
	
	@Override
	public void save(DescuentoForm descuentoForm, Integer idAlumno) throws ParseException {
		AlumnoDescuento alumnoDescuento = new AlumnoDescuento();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		double diferencia = 0.0;
		double montoDescuento;
		
			montoDescuento = descuentoForm.getMonto();
			alumnoDescuento.setFechaFin(formatter.parse(descuentoForm.getFechaFin()));
			alumnoDescuento.setFechaInicio(formatter.parse(descuentoForm.getFechaInicio()));
			alumnoDescuento.setDescuento(descuentoForm.getMonto());
			alumnoDescuento.setIdAlumno(idAlumno);
			
			//Guardar el descuento
			alumnoDescuentoRepository.save(alumnoDescuento);
			
			//Obtener los pagos donde se aplica la beca
			List<AlumnoPago> lstPagos = alumnoBecaRepository.findPagosAplicaBeca(idAlumno, alumnoDescuento.getFechaInicio(), alumnoDescuento.getFechaFin());
			
			//Modificar los pagos y sumar la diferencia de los pagos
			for(AlumnoPago pago : lstPagos){
				
				pago.setMonto(pago.getMonto()-montoDescuento);
				
				if(pago.getPago()>pago.getMonto()){
					diferencia = diferencia + (pago.getPago()-pago.getMonto());
				}
				
				alumnoPagoRepository.save(pago);
			}
			
			//Establecer la diferencia en el alumno
			Alumno alumno = alumnoRepository.findOne(idAlumno);
			alumno.setSaldo(alumno.getSaldo()+diferencia);
			alumnoRepository.save(alumno);
	}

	@Override
	public DescuentoForm findOne(Integer idBeca) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DescuentoForm> findListAlumno(Integer idAlumno) {
		List<DescuentoForm> lst = null;
		List<AlumnoDescuento> lstAlumno;
		DescuentoForm form;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		lstAlumno = alumnoDescuentoRepository.findByIdAlumno(idAlumno);
		
		if(lstAlumno != null){
			lst = new ArrayList<DescuentoForm>();
			for(AlumnoDescuento alumno : lstAlumno){
				form = new DescuentoForm();
				form.setDescuentoId(alumno.getIdDescuento());
				form.setMonto(alumno.getDescuento());
				form.setFechaInicio(formatter.format(alumno.getFechaInicio()));
				form.setFechaFin(formatter.format(alumno.getFechaFin()));
				lst.add(form);
			}
		}
		return lst;
	}

}
