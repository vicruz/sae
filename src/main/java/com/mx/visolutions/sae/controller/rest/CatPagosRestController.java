package com.mx.visolutions.sae.controller.rest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mx.visolutions.sae.entities.CatPagos;
import com.mx.visolutions.sae.entities.PagoGrado;
import com.mx.visolutions.sae.json.CatPagosJson;
import com.mx.visolutions.sae.json.JSon;
import com.mx.visolutions.sae.services.CatPagosService;
import com.mx.visolutions.sae.services.PagoGradoService;

@RestController
@RequestMapping(path="/catpagos")
public class CatPagosRestController {
	
	private CatPagosService catPagosService;
	private PagoGradoService pagoGradoService;

	@Autowired
	public CatPagosRestController(CatPagosService catPagosService, PagoGradoService pagoGradoService){
		this.catPagosService = catPagosService;
		this.pagoGradoService = pagoGradoService;
	}
	
	@RequestMapping(path="/getMonto/{idPagoGrado}", method = RequestMethod.GET)
	public String getMonto(@PathVariable("idPagoGrado") Integer idPagoGrado){
		//el valor que llega es idPagoGrado
		//PagoGrado pagoGrado = pagoGradoService.findOne(idPagoGrado);
		CatPagos catPago = catPagosService.findById(idPagoGrado);
		
		if(catPago==null){
			return "0";
		}
		else{
			return catPago.getMonto().toString();
		}
	}

	
	
	@RequestMapping(value="/addConcepto",method = RequestMethod.POST)
	public JSon adminRest(){
		System.out.println("adminREST");
		JSon value = new JSon();
		System.out.println("alumno rest");
		//model.addAttribute("name","vic");
		//model.addAttribute(new SignupForm());
		
		List<CatPagosJson> lstJson = new ArrayList<CatPagosJson>();
		
	List<CatPagos> lstPagos = catPagosService.findAll();
		
		if(lstPagos!=null){
			for(CatPagos pagos : lstPagos){
				CatPagosJson json = new CatPagosJson();
				json.setConcepto(pagos.getConcepto());
				json.setFecha(String.valueOf(pagos.getFechaAlta()));
				json.setId(pagos.getId());
				json.setMonto(pagos.getMonto());
				json.setUrl("/borrar/"+pagos.getId());
				lstJson.add(json);
			}
		}
		
		value.setData(lstJson);
		return value;
	}
	
	@RequestMapping(path="/getAnio", method = RequestMethod.GET)
	public String getAnio(){
		Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        
		return String.valueOf(año);
	}
	

	
}
