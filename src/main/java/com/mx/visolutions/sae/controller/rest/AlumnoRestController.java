package com.mx.visolutions.sae.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mx.visolutions.sae.entities.Alumno;
import com.mx.visolutions.sae.json.AlumnoJson;
import com.mx.visolutions.sae.json.JSon;
import com.mx.visolutions.sae.repositories.AlumnoRepository;

@RestController
public class AlumnoRestController {
	
private static final Logger logger = LoggerFactory.getLogger(AlumnoRestController.class);
	
	private AlumnoRepository alumnoRepository;
	
	public AlumnoRestController(AlumnoRepository alumnoRepository){
		this.alumnoRepository = alumnoRepository;
	}
	
	@RequestMapping(value="/alumnoRest", method = RequestMethod.POST)
	public JSon alumnosRest() {
		JSon value = new JSon();
		//model.addAttribute("name","vic");
		//model.addAttribute(new SignupForm());
		logger.info("Alumnos GET request");
		List<AlumnoJson> lstJson = new ArrayList<AlumnoJson>();
		
		List<Alumno> lstAlumno = alumnoRepository.findAll();
		
		if(lstAlumno!=null){
			for(Alumno alumno : lstAlumno){
				AlumnoJson json = new AlumnoJson();
				json.setApMaterno(alumno.getApMaterno());
				json.setApPaterno(alumno.getApPaterno());
				json.setId(alumno.getId());
				json.setNombre(alumno.getNombre());
				json.setGrado(alumno.getGrado().getName());
				switch(alumno.getGrado().getId()){
					case 1:
						json.setSemaforo("<span class=\"label label-sm label-success\">Pagado</span>");
						break;
					case 2:
						json.setSemaforo("<span class=\"label label-sm label-warning\">Parcial</span>");
						break;
					case 3:
						json.setSemaforo("<span class=\"label label-sm label-danger\">Adeudo</span>");
						break;
					case 4:
						json.setSemaforo("<span class=\"label label-sm label-success\">Pagado</span>");
						break;
					case 5:
						json.setSemaforo("<span class=\"label label-sm label-warning\">Parcial</span>");
						break;
					case 6:
						json.setSemaforo("<span class=\"label label-sm label-danger\">Adeudo</span>");
						break;
					case 7:
						json.setSemaforo("<span class=\"label label-sm label-success\">Pagado</span>");
						break;
					case 8:
						json.setSemaforo("<span class=\"label label-sm label-warning\">Parcial</span>");
						break;
					case 9:
						json.setSemaforo("<span class=\"label label-sm label-danger\">Adeudo</span>");
						break;
					default:
						json.setGrado("Baja");
						json.setSemaforo("<span class=\"label label-sm label-success\">Pagado</span>");
						break;
					
						
				}
				json.setUrl("/alumnos/"+alumno.getId()+"/pagos");
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
