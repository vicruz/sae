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
import com.mx.visolutions.sae.util.MyUtil;

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
			if(alumnoForm.getFechaPago()!=null){
				alumnoPago.setFechaPago(Calendar.getInstance().getTime());				
			}
			alumnoPago.setPagoGrado(pagoGrado);
			if(alumnoForm.getPago()==0.0){
				alumnoPago.setIdSemaforo(4);
			}else if(alumnoForm.getPago().compareTo(alumnoForm.getMonto())==0){
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
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public AlumnoPagoForm updatePago(Integer idPago, Double pago, Integer idUsuario) {
		AlumnoPago alumnoPago = alumnoPagoRepository.findOne(idPago);
		AlumnoPagoForm alumnoPagoForm = new AlumnoPagoForm();
		
		Double pagoOriginal = alumnoPago.getPago();
		pagoOriginal = pagoOriginal + pago;

		alumnoPago.setPago(pagoOriginal);
		Integer idSemaforo = alumnoPago.getIdSemaforo();
		
		//Pago completo
		if(alumnoPago.getMonto()<=pagoOriginal){
			alumnoPago.setIdSemaforo(1);
		}
		//Pago parcial
		else if(alumnoPago.getMonto()>pagoOriginal){
			//Si el pago es pendiente, el semaforo se pone en parcial
			if(idSemaforo==4){
				alumnoPago.setIdSemaforo(2);
			}
		}
		alumnoPago.setFechaPago(Calendar.getInstance().getTime());
		alumnoPagoRepository.save(alumnoPago);
		
	
		alumnoPagoForm.setId(alumnoPago.getId());
		alumnoPagoForm.setConcepto(alumnoPago.getPagoGrado().getCatPago().getConcepto() + " " +
				MyUtil.getMonth(alumnoPago.getPagoGrado().getMes_corresponde())+ " " +
				alumnoPago.getPagoGrado().getAnio_corresponde());
		alumnoPagoForm.setFechaPago(alumnoPago.getFechaPago());
		alumnoPagoForm.setIdAlumno(alumnoPago.getIdAlumno());
		//alumnoPagoForm.setIdConcepto(//alumnoPago.*);
		alumnoPagoForm.setMonto(alumnoPago.getMonto());
		alumnoPagoForm.setPago(alumnoPago.getPago());
		
		if(alumnoPago.getIdSemaforo()==1){alumnoPagoForm.setSemaforo("<span class=\"label label-sm label-success\">Pagado</span>");}
		else if(alumnoPago.getIdSemaforo()==2){alumnoPagoForm.setSemaforo("<span class=\"label label-sm label-warning\">Parcial</span>");}
		else if(alumnoPago.getIdSemaforo()==3){alumnoPagoForm.setSemaforo("<span class=\"label label-sm label-danger\">Adeudo</span>");}
		else if(alumnoPago.getIdSemaforo()==4){alumnoPagoForm.setSemaforo("<span class=\"label label-sm label-info\">Pendiente</span>");}
		
		//TODO falta actualizar una tabla de bitacoras con los pagos respectivos
		
		return alumnoPagoForm;

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
