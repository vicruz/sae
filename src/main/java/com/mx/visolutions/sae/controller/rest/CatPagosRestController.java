package com.mx.visolutions.sae.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mx.visolutions.sae.dto.CatPagosForm;
import com.mx.visolutions.sae.dto.SignupForm;
import com.mx.visolutions.sae.entities.CatPagos;
import com.mx.visolutions.sae.entities.User;
import com.mx.visolutions.sae.json.CatPagosJson;
import com.mx.visolutions.sae.json.JSon;
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
	
	
}
