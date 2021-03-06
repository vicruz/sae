package com.mx.visolutions.sae.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mx.visolutions.sae.entities.Grado;
import com.mx.visolutions.sae.json.GradoJson;
import com.mx.visolutions.sae.json.JSon;
import com.mx.visolutions.sae.services.GradoService;

@RestController
public class GradoRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(GradoRestController.class);
	
	private GradoService gradoService;
	
	public GradoRestController(GradoService gradoService){
		this.gradoService = gradoService;
	}

	@RequestMapping(value="/gradoRest", method = RequestMethod.POST)
	public JSon gradosRest() {
		JSon value = new JSon();
		logger.info("GradoRest - POST");

		List<GradoJson> lstJson = new ArrayList<GradoJson>();
		
		List<Grado> lstGrado = gradoService.findAll();
		
		if(lstGrado!=null){
			for(Grado grado : lstGrado){
				GradoJson json = new GradoJson();
				json.setIdGrado(grado.getId());
				json.setGrado(grado.getName());
				json.setUrlEditar("/grados/"+grado.getId()+"/editar");
				lstJson.add(json);
			}
		}
		
		value.setData(lstJson);
		//value.setDraw(3);
		//value.setRecordsTotal(lstJson.size());
		//value.setRecordsFiltered(lstJson.size());
		
		return value;
	}
	
}
