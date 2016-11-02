package com.mx.visolutions.sae.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mx.visolutions.sae.entities.CatPagos;
import com.mx.visolutions.sae.services.CatPagosService;

@RestController
@RequestMapping(path="/catpagos")
public class CatPagosRestController {
	
	private CatPagosService catPagosService;

	@Autowired
	public CatPagosRestController(CatPagosService catPagosService){
		this.catPagosService = catPagosService;
	}
	
	@RequestMapping(path="/getMonto/{idPago}", method = RequestMethod.GET)
	public String getMonto(@PathVariable("idPago") Integer idPago){
		CatPagos pago = catPagosService.findByPagoGradoId(idPago);//findById(idPago);
		if(pago==null){
			return "0";
		}
		else{
			return pago.getMonto().toString();
		}
	}
}
