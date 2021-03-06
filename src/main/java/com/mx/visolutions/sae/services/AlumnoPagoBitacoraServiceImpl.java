package com.mx.visolutions.sae.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mx.visolutions.sae.entities.Alumno;
import com.mx.visolutions.sae.entities.AlumnoPagoBitacora;
import com.mx.visolutions.sae.entities.AlumnoReportDailyVO;
import com.mx.visolutions.sae.repositories.AlumnoPagoBitacoraRepository;
import com.mx.visolutions.sae.repositories.AlumnoRepository;
import com.mx.visolutions.sae.util.MyUtil;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class AlumnoPagoBitacoraServiceImpl implements AlumnoPagoBitacoraService {

	private AlumnoPagoBitacoraRepository alumnoPagoBitacoraRepository; 
	private AlumnoRepository alumnoRepository; 
	
	@Autowired
	public AlumnoPagoBitacoraServiceImpl(AlumnoPagoBitacoraRepository alumnoPagoBitacoraRepository,
			AlumnoRepository alumnoRepository){
		this.alumnoPagoBitacoraRepository = alumnoPagoBitacoraRepository;
		this.alumnoRepository = alumnoRepository;
	}
	
	@Override
	public List<AlumnoReportDailyVO> getPagosBetweenFechaPago(Date fechaInicio, Date fechaFin) {
		List<AlumnoReportDailyVO> lst = new ArrayList<AlumnoReportDailyVO>();
		AlumnoReportDailyVO vo;
		Alumno alumno;
		
		List<AlumnoPagoBitacora> lstAlumnoPago = alumnoPagoBitacoraRepository.findByFechaPagoBetween(fechaInicio, fechaFin);
		
		for (AlumnoPagoBitacora alumnoPagoBitacora : lstAlumnoPago) {
			vo = new AlumnoReportDailyVO();
			
			alumno = alumnoRepository.findOne(alumnoPagoBitacora.getAlumnoPago().getIdAlumno());
			
			vo.setConcepto(alumnoPagoBitacora.getAlumnoPago().getPagoGrado().getCatPago().getConcepto()+" "+
					MyUtil.getMonth(alumnoPagoBitacora.getAlumnoPago().getPagoGrado().getMes_corresponde())+" "+
					alumnoPagoBitacora.getAlumnoPago().getPagoGrado().getAnio_corresponde());
			vo.setGrado(MyUtil.getGrado(alumnoPagoBitacora.getAlumnoPago().getPagoGrado().getIdGrado()));
			vo.setNombre(alumno.getNombre()+" "+alumno.getApPaterno()+" "+alumno.getApMaterno());
			vo.setPago(alumnoPagoBitacora.getPago().floatValue());
			vo.setSaldo(alumnoPagoBitacora.getSaldo());

			lst.add(vo);
		}
		
		return lst;
	}

}
