package com.mx.visolutions.sae.controller;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mx.visolutions.sae.dto.AlumnoForm;
import com.mx.visolutions.sae.dto.AlumnoPagoForm;
import com.mx.visolutions.sae.dto.BecaForm;
import com.mx.visolutions.sae.entities.Alumno;
import com.mx.visolutions.sae.entities.PagoGrado;
import com.mx.visolutions.sae.services.AlumnoBecaService;
import com.mx.visolutions.sae.services.AlumnoPagoService;
import com.mx.visolutions.sae.services.AlumnoService;
import com.mx.visolutions.sae.services.CatPagosService;
import com.mx.visolutions.sae.services.PagoGradoService;
import com.mx.visolutions.sae.util.MyUtil;

@Controller
public class AlumnoController {

	private static final Logger logger = LoggerFactory.getLogger(AlumnoController.class);
	
	private AlumnoService alumnoService;
	private PagoGradoService pagoGradoService;
	private AlumnoPagoService alumnoPagoService;
	private AlumnoBecaService alumnoBecaService;

	@Autowired
	public AlumnoController(AlumnoService alumnoService,PagoGradoService pagoGradoService,
			AlumnoPagoService alumnoPagoService, CatPagosService catPagosService,
			AlumnoBecaService alumnoBecaService) {
		this.alumnoService = alumnoService;
		this.pagoGradoService = pagoGradoService;
		this.alumnoPagoService = alumnoPagoService;
		this.alumnoBecaService = alumnoBecaService;
	}
	
	
	@RequestMapping(value="/alumnos", method = RequestMethod.GET)
	public String alumnos(Model model) {
		System.out.println("Alumno controller-GET");
		//model.addAttribute("name","vic");
		model.addAttribute(new AlumnoForm());
		//logger.info("Alumnos GET request");
		return "alumnos";
	}
	
	@RequestMapping(value="/alumnos", method = RequestMethod.POST)
	public String signup(@ModelAttribute("alumnoForm") @Valid AlumnoForm alumnoForm,
			BindingResult result, RedirectAttributes redirectAttributes){
		System.out.println("Alumno controller");
		if(result.hasErrors())
			return "alumnos";
			
		logger.info(alumnoForm.toString());
		
		try {
			alumnoService.save(alumnoForm);
			MyUtil.flash(redirectAttributes, "success", "alumnoSaveSuccess");
		} catch (Exception e) {
			//TODO Cambiar el label
			MyUtil.flash(redirectAttributes, "danger", "alumnoSaveNoSuccess",e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return "redirect:/alumnos";
	}
	
	@RequestMapping(value="/alumnos/{alumnoId}/pagos", method=RequestMethod.GET)
	public String pagos(@PathVariable("alumnoId") Integer alumnoId, Model model){
		logger.info("Buscando alumno con id " + alumnoId );
		
		List<PagoGrado> pagoGradosForm;
		Map<String,String> map = new HashMap<String,String>();
		Alumno alumno = alumnoService.findById(alumnoId);

		//Se busca si el alumno tiene beca
		BecaForm becaForm = alumnoBecaService.findByAlumnoAndCurrentDate(alumnoId);
		if(becaForm!=null){
			alumno.setBeca(becaForm.getPorcentaje().longValue());
		}
		
		model.addAttribute(alumno);
		model.addAttribute(new AlumnoPagoForm());
		
		//pagoGradosForm = pagoGradoService.getByIdGrado(alumno.getGrado().getId());
		pagoGradosForm = pagoGradoService.findByIdGradoNotInAlumno(alumno.getGrado().getId(),alumnoId);
		
		for(PagoGrado pagoGrado: pagoGradosForm){
			String concepto = pagoGrado.getCatPago().getConcepto()
					+" " + MyUtil.getMonth(pagoGrado.getMes_corresponde()) 
					+" " + pagoGrado.getAnio_corresponde();
			map.put(""+pagoGrado.getId(), concepto + " - $" + pagoGrado.getCatPago().getMonto());
		}
		
		model.addAttribute("conceptos", map);
		//model.addAttribute("lstPagoGradoService", pagoGradosForm);
		
		return "alumnoPago";
	}
	
	@RequestMapping(value="/alumnos/{alumnoId}/pagos", method=RequestMethod.POST)
	public String pagosPost(@PathVariable("alumnoId") Integer alumnoId, 
			@ModelAttribute("alumnoPagoForm") @Valid AlumnoPagoForm alumnoPagoForm,
			BindingResult result, RedirectAttributes redirectAttributes, Model model){

		if(result.hasErrors())
			return "alumnos";

		alumnoPagoForm.setIdAlumno(alumnoId);
		alumnoPagoForm.setFechaPago(Calendar.getInstance().getTime());
	
		logger.info("alumnoPagoForm: " + alumnoPagoForm.toString());
		try {
			alumnoPagoService.save(alumnoPagoForm);
			MyUtil.flash(redirectAttributes, "success", "pagoSuccess");
		} catch (Exception e) {
			//TODO Cambiar el label
			MyUtil.flash(redirectAttributes, "danger", "pagoNoSuccess", e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return pagos(alumnoId,model);
		//return "redirect:/alumnoPago"+alumnoId+"/pagos";
		//return "alumnoPago";
	}
	
	@RequestMapping(value="/alumnos/{alumnoId}/editar", method=RequestMethod.GET)
	public String editar(@PathVariable("alumnoId") Integer alumnoId, Model model){
		logger.info("Editar alumno: " + alumnoId );
		
		AlumnoForm alumnoForm = new AlumnoForm();
		Alumno alumno = alumnoService.findById(alumnoId);
		
		alumnoForm.setId(alumno.getId());
		alumnoForm.setApMaterno(alumno.getApMaterno());
		alumnoForm.setApPaterno(alumno.getApPaterno());
		alumnoForm.setGradoId(alumno.getGrado().getId());
		alumnoForm.setNombre(alumno.getNombre());
		
		model.addAttribute(alumnoForm);
		model.addAttribute(new BecaForm());
				
		return "alumnoEdit";
	}
	
	@RequestMapping(value="/alumnos/{alumnoId}/editar", method=RequestMethod.POST)
	public String editarPost(@PathVariable("alumnoId") Integer alumnoId, 
			@ModelAttribute("alumnoPagoForm") @Valid AlumnoForm alumnoForm,
			//@ModelAttribute("becaForm") @Valid BecaForm becaForm,
			BindingResult result, RedirectAttributes redirectAttributes, Model model){

		if(result.hasErrors())
			return "alumnos";

		alumnoForm.setId(alumnoId);
	
		logger.info("Update alumnoForm: " + alumnoForm.toString());
		try {
			alumnoService.update(alumnoForm);
			MyUtil.flash(redirectAttributes, "success", "alumnoUpdateSuccess");
		} catch (Exception e) {
			//TODO Cambiar el label
			MyUtil.flash(redirectAttributes, "danger", "alumnoUpdateNoSuccess", e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return "redirect:/alumnos";
	}
	
	@RequestMapping(value="/alumnos/{alumnoId}/editar/beca", method=RequestMethod.POST)
	public String editarPostBeca(@PathVariable("alumnoId") Integer alumnoId, 
			@ModelAttribute("becaForm") @Valid BecaForm becaForm,
			BindingResult result, RedirectAttributes redirectAttributes, Model model){

		if(result.hasErrors())
			return "redirect:/alumnos/"+alumnoId+"/editar";
		
		
		
		try {
			if(becaForm.getPorcentaje()>100){
				MyUtil.flash(redirectAttributes, "danger", "alumnoSaveBecaPorcentajeError");
			}else{
				alumnoBecaService.save(becaForm, alumnoId);
				MyUtil.flash(redirectAttributes, "success", "alumnoSaveBecaSuccess");				
			}
		} catch (Exception e) {
			MyUtil.flash(redirectAttributes, "danger", "alumnoSaveBecaNoSuccess", e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return "redirect:/alumnos/"+alumnoId+"/editar";
	}
	
}
