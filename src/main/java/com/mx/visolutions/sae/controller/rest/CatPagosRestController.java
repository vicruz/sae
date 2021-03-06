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
//import com.mx.visolutions.sae.entities.PagoGrado;
import com.mx.visolutions.sae.json.CatPagosJson;
import com.mx.visolutions.sae.json.JSon;
import com.mx.visolutions.sae.services.CatPagosService;
import com.mx.visolutions.sae.services.PagoGradoService;
//import com.mx.visolutions.sae.services.PagoGradoService;
import com.mx.visolutions.sae.util.MyUtil;

@RestController
@RequestMapping(path="/catpagos")
public class CatPagosRestController {
	
	private CatPagosService catPagosService;
	private PagoGradoService pagoGradoService;

	@Autowired
	public CatPagosRestController(PagoGradoService pagoGradoService, CatPagosService catPagosService){//, ){
		this.catPagosService = catPagosService;
		this.pagoGradoService = pagoGradoService;
	}
	
	@RequestMapping(path="/getMonto/{idPagoGrado}", method = RequestMethod.GET)
	public String getMonto(@PathVariable("idPagoGrado") Integer idPagoGrado){
		//el valor que llega es idPagoGrado
		PagoGrado pagoGrado = pagoGradoService.findOne(idPagoGrado);
		//CatPagos catPago = catPagosService.findById(idPagoGrado);
		
		if(pagoGrado==null){
			return "0";
		}
		else{
			return pagoGrado.getCatPago().getMonto().toString();
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
				json.setBeca(pagos.getAplicaBeca()==1?"<span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\">":"<span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\">");
				json.setGeneraAdeudo(pagos.getGeneraAdeudo()==1?"<span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\">":"<span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\">");
				json.setPagoUnico(pagos.getPagoUnico()==1?"<span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\">":"<span class=\"glyphicon glyphicon-remove\" aria-hidden=\"true\">");
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
	
	@RequestMapping(path="/add", method = RequestMethod.POST)
	public String addPay(){
		//Agrega pagos
/*
		try {
			catPagosService.addNuevoPago(catPagosForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
		return "";
	}
	
	@RequestMapping(path="/modify", method = RequestMethod.POST)
	public String modifyPay(){
		//Agrega pagos
/*
		try {
			catPagosService.addNuevoPago(catPagosForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
		return "";
	}
	

	
}
