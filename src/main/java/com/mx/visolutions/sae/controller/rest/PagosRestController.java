package com.mx.visolutions.sae.controller.rest;

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

import com.mx.visolutions.sae.entities.AlumnoPago;
import com.mx.visolutions.sae.entities.PagoGrado;
import com.mx.visolutions.sae.json.AlumnoPagoJson;
import com.mx.visolutions.sae.json.JSon;
import com.mx.visolutions.sae.json.PagoGradoJson;
import com.mx.visolutions.sae.repositories.AlumnoPagoRepository;
import com.mx.visolutions.sae.services.PagoGradoService;
import com.mx.visolutions.sae.util.MyUtil;


@RestController
public class PagosRestController {

	private static final Logger logger = LoggerFactory.getLogger(PagosRestController.class);
	
	private AlumnoPagoRepository alumnoPagoRepository;
	private PagoGradoService pagoGradoService;
	
	@Autowired
	public PagosRestController(AlumnoPagoRepository alumnoPagoRepository,
			PagoGradoService pagoGradoService){
		this.alumnoPagoRepository = alumnoPagoRepository;
		this.pagoGradoService = pagoGradoService;
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
				json.setConcepto(alumnoPago.getPagoGrado().getCatPago().getConcepto() + " " +
						MyUtil.getMonth(alumnoPago.getPagoGrado().getMes_corresponde())+ " " +
						alumnoPago.getPagoGrado().getAnio_corresponde());
				json.setFecha(sdf.format(alumnoPago.getFechaPago()));
				json.setMonto(alumnoPago.getMonto());
				json.setPago(alumnoPago.getPago());
				
				//TODO debe obtener el valor del catalogo de semaforos
				if(alumnoPago.getIdSemaforo()==1){json.setEstatus("<span class=\"label label-sm label-success\">Pagado</span>");}
				else if(alumnoPago.getIdSemaforo()==2){json.setEstatus("<span class=\"label label-sm label-warning\">Parcial</span>");}
				else if(alumnoPago.getIdSemaforo()==3){json.setEstatus("<span class=\"label label-sm label-danger\">Adeudo</span>");}
				
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
	
	
}
