package com.mx.visolutions.sae.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mx.visolutions.sae.dto.AlumnoPagoForm;
import com.mx.visolutions.sae.entities.AlumnoPago;
import com.mx.visolutions.sae.entities.PagoGrado;
import com.mx.visolutions.sae.repositories.AlumnoPagoRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class AlumnoPagoServiceImpl implements AlumnoPagoService {
	
	private AlumnoPagoRepository alumnoPagoRepository;
	
	@Autowired
	public AlumnoPagoServiceImpl(AlumnoPagoRepository alumnoPagoRepository) {
		this.alumnoPagoRepository = alumnoPagoRepository;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<AlumnoPago> findAll() {
		return alumnoPagoRepository.findAll();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void save(AlumnoPagoForm alumnoForm) throws Exception {
		AlumnoPago alumnoPago = new AlumnoPago();
		
		if(alumnoForm!=null){
			PagoGrado pagoGrado = new PagoGrado();
			pagoGrado.setId(alumnoForm.getIdPagoGrado());
			
			alumnoPago.setIdAlumno(alumnoForm.getIdAlumno());
			alumnoPago.setMonto(alumnoForm.getMonto());
			alumnoPago.setPago(alumnoForm.getPago());
			alumnoPago.setFechaPago(Calendar.getInstance().getTime());
			alumnoPago.setPagoGrado(pagoGrado);
			if(alumnoForm.getPago().compareTo(alumnoForm.getMonto())==0){
				alumnoPago.setIdSemaforo(1);
			}else if(alumnoForm.getPago()>0 && alumnoForm.getPago()<alumnoForm.getMonto()){
				alumnoPago.setIdSemaforo(2);
			}else{
				alumnoPago.setIdSemaforo(3);
			}
			
			alumnoPagoRepository.save(alumnoPago);
		}
		

	}

	@Override
	public void update(AlumnoPagoForm alumnoForm) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AlumnoPagoForm> findByIdAlumno(Integer id) {

		List<AlumnoPagoForm> alumnosPagoForm = new ArrayList<AlumnoPagoForm>();
		List<AlumnoPago> alumnosPago;
		AlumnoPagoForm alumnoPagoForm;
				
		alumnosPago = alumnoPagoRepository.findByIdAlumno(id);
		
		for(AlumnoPago alumno: alumnosPago){
			alumnoPagoForm = new AlumnoPagoForm();
			alumnoPagoForm.setFechaPago(alumno.getFechaPago());
			alumnoPagoForm.setId(alumno.getId());
			alumnoPagoForm.setMonto(alumno.getMonto());
			alumnoPagoForm.setPago(alumno.getPago());
			if(alumno.getIdSemaforo()==1){
				alumnoPagoForm.setSemaforo("Pagado");
			}else if(alumno.getIdSemaforo()==2){
				alumnoPagoForm.setSemaforo("Parcial");
			}else if(alumno.getIdSemaforo()==3){
				alumnoPagoForm.setSemaforo("Adeudo");
			}
			
		}
		
		return alumnosPagoForm;
	}

	@Override
	public void delete(AlumnoPagoForm alumnoForm) { 
		// TODO Auto-generated method stub

	}

	@Override
	public void getList() {
		// TODO Auto-generated method stub

	}

}
