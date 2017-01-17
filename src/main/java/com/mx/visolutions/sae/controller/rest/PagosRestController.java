package com.mx.visolutions.sae.controller.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mx.visolutions.sae.dto.AlumnoPagoForm;
import com.mx.visolutions.sae.entities.AlumnoPago;
import com.mx.visolutions.sae.entities.PagoGrado;
import com.mx.visolutions.sae.json.AlumnoPagoJson;
import com.mx.visolutions.sae.json.JSon;
import com.mx.visolutions.sae.json.PagoGradoJson;
import com.mx.visolutions.sae.repositories.AlumnoPagoRepository;
import com.mx.visolutions.sae.services.AlumnoPagoService;
import com.mx.visolutions.sae.services.PagoGradoService;
import com.mx.visolutions.sae.util.MyUtil;


@RestController
public class PagosRestController {

	private static final Logger logger = LoggerFactory.getLogger(PagosRestController.class);
	
	private AlumnoPagoRepository alumnoPagoRepository;
	private AlumnoPagoService alumnoPagoService;
	private PagoGradoService pagoGradoService;
	
	@Autowired
	public PagosRestController(AlumnoPagoRepository alumnoPagoRepository,
			PagoGradoService pagoGradoService, AlumnoPagoService alumnoPagoService){
		this.alumnoPagoRepository = alumnoPagoRepository;
		this.pagoGradoService = pagoGradoService;
		this.alumnoPagoService = alumnoPagoService;
	}
	
	@RequestMapping(value="/pagosRest/{id}", method = RequestMethod.POST)
	public JSon findPagosXAlumno(@PathVariable("id") Integer id){
		JSon value = new JSon();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		logger.info("Buscando los pagos del alumno: " + id);
		
		List<AlumnoPago> lstAlumnosPago = alumnoPagoRepository.findByIdAlumno(id);
		List<AlumnoPagoJson> lstJson = new ArrayList<AlumnoPagoJson>();
		if(lstAlumnosPago != null){
			for(AlumnoPago alumnoPago : lstAlumnosPago){
				AlumnoPagoJson json = new AlumnoPagoJson();
				json.setId(alumnoPago.getId());
				json.setIdAlumno(id);
				json.setConcepto(alumnoPago.getPagoGrado().getCatPago().getConcepto() + " " +
						MyUtil.getMonth(alumnoPago.getPagoGrado().getMes_corresponde())+ " " +
						alumnoPago.getPagoGrado().getAnio_corresponde());
				if(alumnoPago.getFechaPago()!=null){
					json.setFecha(sdf.format(alumnoPago.getFechaPago()));					
				}
				json.setMonto(alumnoPago.getMonto());
				json.setPago(alumnoPago.getPago());
				
				//TODO debe obtener el valor del catalogo de semaforos
				if(alumnoPago.getIdSemaforo()==1){json.setEstatus("<span class=\"label label-sm label-success\">Pagado</span>");}
				else if(alumnoPago.getIdSemaforo()==2){json.setEstatus("<span class=\"label label-sm label-warning\">Parcial</span>");}
				else if(alumnoPago.getIdSemaforo()==3){json.setEstatus("<span class=\"label label-sm label-danger\">Adeudo</span>");}
				else if(alumnoPago.getIdSemaforo()==4){json.setEstatus("<span class=\"label label-sm label-info\">Pendiente</span>");}
				
				if(alumnoPago.getIdSemaforo()==1){json.setEditar("<button type=\"button\" class=\"btn-table disabled-btn-table\">Pagar</button>"); }
				else{ json.setEditar("<button type=\"button\" class=\"btn-table\" >Pagar</button>"); }
				
				if(alumnoPago.getFechaLimite()!=null){
					json.setFechaLimite(sdf.format(alumnoPago.getFechaLimite()));					
				}
				
				lstJson.add(json);
			}
		}
		
		value.setData(lstJson);
		return value;
	}
	
	
	/**
	 * Obtiene los pagos para el grado del alumno
	 * @param idGrado Grado del alumno
	 * @return JSON con mapa de id - conepto
	 */
	@RequestMapping(value="/pagosRest/getCatalogo/{idGrado}", method = RequestMethod.GET)
	public List<PagoGradoJson> getCatalogoPago(@PathVariable("idGrado") Integer idGrado){
		
		logger.info("Buscando los conceptos de pago por alumno: " + idGrado);
		
		List<PagoGrado> lstPagoGrados = pagoGradoService.getByIdGrado(idGrado);
		List<PagoGradoJson> lst = new ArrayList<PagoGradoJson>();
		PagoGradoJson json = new PagoGradoJson();

		//Seleccionar
		json.setIdPagoGrado(0);
		json.setConcepto("Seleccionar");
		json.setMonto(0.0);
		
		lst.add(json);
		
		if(lstPagoGrados != null){
			for(PagoGrado pagoGrado : lstPagoGrados){
				json = new PagoGradoJson();
				json.setIdPagoGrado(pagoGrado.getId());
				json.setMonto(pagoGrado.getCatPago().getMonto());
				json.setConcepto(pagoGrado.getCatPago().getConcepto()
						+" " + MyUtil.getMonth(pagoGrado.getMes_corresponde()) 
						+" " + pagoGrado.getAnio_corresponde());
				lst.add(json);
			}
		}
		
		return lst;
	}
	
	
	/**
	 * Actualiza el monto de los pagos por alumno
	 * @param id
	 * @param pago
	 * @param userId
	 * @return
	 */
	@RequestMapping(value="/pagosRest/update/{id}", method = RequestMethod.POST)
	//public AlumnoPagoJson updatePago(@PathVariable("id") Integer id, Double pago, Integer userId, Boolean checked, Double saldo){
	public AlumnoPagoJson updatePago(@PathVariable("id") Integer id, Double pago, Integer userId, Boolean checked){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		logger.info("Sumando el pago: " + pago + " al pago: " + id);
		if(checked==null){
			checked = false;
		}
//		if(saldo==null){
//			saldo = 0.0;
//		}
		
		//AlumnoPagoForm alumno = alumnoPagoService.updatePago(id, pago, userId, checked, saldo);
		AlumnoPagoForm alumno = alumnoPagoService.updatePago(id, pago, userId, checked);
		
		//Se actualiza el estatus del pago
		alumnoPagoService.updateStatusByPago(alumno.getIdAlumno());
		
		
		AlumnoPagoJson json = new AlumnoPagoJson();
		json.setId(alumno.getId());
		json.setIdAlumno(alumno.getIdAlumno());
		json.setConcepto(alumno.getConcepto());
		if(alumno.getFechaPago()!=null){
			json.setFecha(sdf.format(alumno.getFechaPago()));					
		}
		json.setMonto(alumno.getMonto());
		json.setPago(alumno.getPago());
		json.setEstatus(alumno.getSemaforo());
		json.setSaldo(alumno.getSaldo());
		
		if(alumno.getSemaforo().contains("Pagado")){json.setEditar("<button type=\"button\" class=\"btn-table disabled-btn-table\">Pagar</button>"); }
		else{ json.setEditar("<button type=\"button\" class=\"btn-table\" >Pagar</button>"); }
		
		return json;
	}
	
	/**
	 * Actualiza el monto de los pagos por alumno
	 * @param id
	 * @param pago
	 * @param userId
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/pagosRest/fechaLimite/{id}", method = RequestMethod.POST)
	//public AlumnoPagoJson updatePago(@PathVariable("id") Integer id, Double pago, Integer userId, Boolean checked, Double saldo){
	public AlumnoPagoJson updateFechaLimite(@PathVariable("id") Integer id, String fechaLimite) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		logger.info("Actualizar la fecha limite del pago id: " + id + " con la fecha: " + fechaLimite);
		
		AlumnoPagoForm alumno = alumnoPagoService.updateFechaLimite(id, sdf.parse(fechaLimite));
		
		//Se actualiza el estatus del pago
		alumnoPagoService.updateStatusByPago(alumno.getIdAlumno());
		
		AlumnoPagoJson json = new AlumnoPagoJson();
//		json.setId(alumno.getId());
//		json.setIdAlumno(alumno.getIdAlumno());
//		json.setConcepto(alumno.getConcepto());
//		if(alumno.getFechaPago()!=null){
//			json.setFecha(sdf.format(alumno.getFechaPago()));					
//		}
		json.setMonto(alumno.getMonto());
//		json.setPago(alumno.getPago());
		json.setEstatus(alumno.getSemaforo());
//		json.setSaldo(alumno.getSaldo());
		
//		if(alumno.getSemaforo().contains("Pagado")){json.setEditar("<button type=\"button\" class=\"btn-table disabled-btn-table\">Pagar</button>"); }
//		else{ json.setEditar("<button type=\"button\" class=\"btn-table\" >Pagar</button>"); }
		
		return json;
	}
	
	
}
