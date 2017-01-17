package com.mx.visolutions.sae.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mx.visolutions.sae.dto.AlumnoPagoForm;
import com.mx.visolutions.sae.entities.Alumno;
import com.mx.visolutions.sae.entities.AlumnoPago;
import com.mx.visolutions.sae.entities.PagoGrado;
import com.mx.visolutions.sae.repositories.AlumnoPagoRepository;
import com.mx.visolutions.sae.repositories.AlumnoRepository;
import com.mx.visolutions.sae.util.MyUtil;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class AlumnoPagoServiceImpl implements AlumnoPagoService {
	
	private AlumnoPagoRepository alumnoPagoRepository;
	private AlumnoRepository alumnoRepository;
	
	@Autowired
	public AlumnoPagoServiceImpl(AlumnoPagoRepository alumnoPagoRepository, AlumnoRepository alumnoRepository) {
		this.alumnoPagoRepository = alumnoPagoRepository;
		this.alumnoRepository = alumnoRepository;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");	
		
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
			alumnoPago.setFechaLimite(sdf.parse(alumnoForm.getFechaLimite()));
			
			alumnoPagoRepository.save(alumnoPago);
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	//public AlumnoPagoForm updatePago(Integer idPago, Double pago, Integer idUsuario, Boolean checked, Double saldo) {
	public AlumnoPagoForm updatePago(Integer idPago, Double pago, Integer idUsuario, Boolean checked) {
		Alumno alumno = null;
		AlumnoPago alumnoPago = alumnoPagoRepository.findOne(idPago);
		AlumnoPagoForm alumnoPagoForm = new AlumnoPagoForm();
		Double pagoTotal = 0.0;
		Double saldo = 0.0;
		
		if(pago == null){
			pago = 0.0;
		}
		
		//Se obtiene el pago previo del alumno y el monto a pagar
		Double pagoOriginal = alumnoPago.getPago();
		Double monto = alumnoPago.getMonto();
		Integer idSemaforo = alumnoPago.getIdSemaforo();
		
		//Si se utiliza el saldo a favor del pago, se suman los valores de
		//pago y saldo para el pagoTotal. 
		if(checked){
			alumno = alumnoRepository.findOne(alumnoPago.getIdAlumno());
			saldo = alumno.getSaldo();
			pagoTotal = pago + saldo;
		}else{
			pagoTotal = pago;
		}
		
		//Se suma el nuevo pago al pago original
		pagoOriginal = pagoOriginal + pagoTotal;
		
		//Si la suma de los pagos es mayor al monto a pagar, el residuo queda en el saldo
		//Se reduce el pago original al valor del monto
		if(pagoOriginal>=monto){
			saldo = pagoOriginal-monto;
			pagoOriginal = monto;
		}
		
		//Si el check es true y el pago original es menor a al monto, el saldo se va a 0
		if(checked && (pagoOriginal<monto)){
			saldo = 0.0;
		}

		alumnoPago.setPago(pagoOriginal);
		
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
		
		//Se actualiza el saldo
		if(checked){
			//Alumno alumno = alumnoRepository.findOne(alumnoPago.getIdAlumno());
			alumno.setSaldo(saldo);
			alumnoRepository.save(alumno);
		}
		
	
		alumnoPagoForm.setId(alumnoPago.getId());
		alumnoPagoForm.setConcepto(alumnoPago.getPagoGrado().getCatPago().getConcepto() + " " +
				MyUtil.getMonth(alumnoPago.getPagoGrado().getMes_corresponde())+ " " +
				alumnoPago.getPagoGrado().getAnio_corresponde());
		alumnoPagoForm.setFechaPago(alumnoPago.getFechaPago());
		alumnoPagoForm.setIdAlumno(alumnoPago.getIdAlumno());
		//alumnoPagoForm.setIdConcepto(//alumnoPago.*);
		alumnoPagoForm.setMonto(alumnoPago.getMonto());
		alumnoPagoForm.setPago(alumnoPago.getPago());
		alumnoPagoForm.setSaldo(saldo);
		
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

	@Override
	public void updateMontoFechaExceed() {
		Calendar fechaActual = Calendar.getInstance();
		List<AlumnoPago> lstAlumno = alumnoPagoRepository.findPagoLimitExceed(1,fechaActual.getTime());
		int mesesDiff;
		Double montoOriginal;
		Double montoCalculado;
		int porcentaje;
		Set<Integer> set = new HashSet<Integer>();
		Alumno alumno;
		
		//Se modifican los montos de cada pago
		for(AlumnoPago alumnoPago : lstAlumno){
			
			mesesDiff = MyUtil.calcularMesesAFecha(alumnoPago.getFechaLimite(), fechaActual.getTime());
			
			//Se agrega un mes a la diferencia de meses ya que si solo ha pasado 1 día de retardo no
			//se hará modificación alguna
			mesesDiff +=1;
			
			//////////////////////////////////
			//Calculo del % a sumar al pago
			/////////////////////////////////
			
			//Monto original
			montoOriginal = alumnoPago.getPagoGrado().getCatPago().getMonto();
			
			//Porcentaje
			porcentaje = mesesDiff * 10;
			
			//Monto calculado
			montoCalculado = montoOriginal + ((montoOriginal*porcentaje)/100);
			
			//Nuevo monto
			alumnoPago.setMonto(montoCalculado);
			alumnoPago.setIdSemaforo(3);
			
			//Se agrega el Id del alumno modificado a la lista para posteriormente modificar su estatus
			set.add(alumnoPago.getIdAlumno());
			
			//////////////////////////
			//Se actualiza el pago
			//////////////////////////
			alumnoPagoRepository.save(alumnoPago);
		}
		
		//Una vez modificados los pagos, se modifica el estatus de cada alumno
		for(Integer idAlumno : set){
			alumno = alumnoRepository.findOne(idAlumno);
			alumno.setIdSemaforo(3); //Adeudo
			alumnoRepository.save(alumno);
		}
		
	}

	@Override
	public void updateStatusByPago(Integer idAlumno) {
		Alumno alumno = alumnoRepository.findOne(idAlumno);
		long idSemaforo = alumno.getIdSemaforo();
		
		//Obtener la lista de los pagos del alumno
		List<AlumnoPago> lst = alumnoPagoRepository.findByIdAlumno(idAlumno);
		
		//Hacer un Set para descartar valores repetidos
		Set<Integer> set = new HashSet<Integer>();
		for(AlumnoPago alumnoPago : lst){
			set.add(alumnoPago.getIdSemaforo());
		}
		
		if(set.contains(3)){
			idSemaforo = 3;
		}else if(set.contains(2)){
			idSemaforo = 2;
		}else if(set.contains(1)){
			idSemaforo = 1;
		}else {
			idSemaforo = 4;
		}
		
		//Se actualiza el semaforo del alumno
		if(idSemaforo != alumno.getIdSemaforo()){
			alumno.setIdSemaforo(idSemaforo);
			alumnoRepository.save(alumno);
		}
		
		
	}

	@Override
	public AlumnoPagoForm updateFechaLimite(Integer idPago, Date fechaLimite) {
		AlumnoPago alumnoPago = alumnoPagoRepository.findOne(idPago);
		AlumnoPagoForm alumnoPagoForm = new AlumnoPagoForm();
		
		alumnoPago.setFechaLimite(fechaLimite);
		alumnoPago.setMonto(alumnoPago.getPagoGrado().getCatPago().getMonto());
		alumnoPago.setIdSemaforo(4); //Se pone el estatus como Pendiente
		
		alumnoPagoRepository.save(alumnoPago);
		//updateStatusByPago(alumnoPago.getIdAlumno());
		
		alumnoPagoForm.setIdAlumno(alumnoPago.getIdAlumno());
		alumnoPagoForm.setMonto(alumnoPago.getMonto());
		alumnoPagoForm.setSemaforo("<span class=\"label label-sm label-info\">Pendiente</span>");
		
		return alumnoPagoForm;
	}
	
	
	


}
