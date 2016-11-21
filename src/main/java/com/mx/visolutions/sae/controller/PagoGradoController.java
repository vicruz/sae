/**
 * 
 */
package com.mx.visolutions.sae.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mx.visolutions.sae.dto.PagoGradoRelForm;
import com.mx.visolutions.sae.entities.CatPagos;
import com.mx.visolutions.sae.entities.Grado;
import com.mx.visolutions.sae.services.AlumnoPagoService;
import com.mx.visolutions.sae.services.CatPagosService;
import com.mx.visolutions.sae.services.GradoService;
import com.mx.visolutions.sae.services.PagoGradoService;
import com.mx.visolutions.sae.services.UserService;
import com.mx.visolutions.sae.util.MyUtil;

@Controller
public class PagoGradoController {

	private PagoGradoService pagoGradoService;
	private CatPagosService catPagosService;
	private GradoService gradoService;
	private static final Logger logger = LoggerFactory.getLogger(PagoGradoController.class);

	@Autowired
	public PagoGradoController(UserService userService,PagoGradoService pagoGradoService,
			AlumnoPagoService alumnoPagoService, CatPagosService catPagosService,GradoService gradoService) {
		this.pagoGradoService = pagoGradoService;
		this.catPagosService = catPagosService;
		this.gradoService	= gradoService;
	}

	@RequestMapping(value="/pagoGrado", method=RequestMethod.GET)
	public String pagoGrado(Model model){
		logger.info("Busca los id pago-catalogo pago");

		List<Grado> grado;
		List<CatPagos> catPagos;
		Map<String,String> map = new HashMap<String,String>();
		Map<String,String> map2 = new HashMap<String,String>();

		model.addAttribute(new PagoGradoRelForm());
		grado = gradoService.findAll();
		catPagos= catPagosService.findAll();

		for(Grado gr : grado){
			String name= gr.getName();
			map.put(""+gr.getId(), name);
		}
		for(CatPagos cat: catPagos){
			String concepto =cat.getConcepto(); 
			map2.put(""+cat.getId(), concepto);
		}
		model.addAttribute("grados", map);
		model.addAttribute("conceptos", map2);

		return "pagoGrado";
	}

	@RequestMapping(value="/pagoGrado", method = RequestMethod.POST)
	public String signup(@ModelAttribute("PagoGradoRelForm") @Valid PagoGradoRelForm pagoGradoRelForm,
			BindingResult result, RedirectAttributes redirectAttributes) throws Throwable {
		
		int tam=pagoGradoRelForm.getFechaLimite().length();
	    int aniolim=Integer.valueOf(pagoGradoRelForm.getFechaLimite().substring(0, 4));
		int anio= Integer.valueOf(pagoGradoRelForm.getAnio());
		int mes= Integer.valueOf(pagoGradoRelForm.getFechaLimite().substring(5, 7));
		int dia=Integer.valueOf(pagoGradoRelForm.getFechaLimite().substring(8, tam));
		
		if((aniolim<anio)|| (mes>12)||(dia>31)){
			MyUtil.flash(redirectAttributes, "danger", "pagoNoSuccess");
			return "redirect:/pagoGrado";
		}
			
		if(result.hasErrors())
			return "redirect:/pagoGrado";
		
		try {
			pagoGradoService.addNew(pagoGradoRelForm);
			MyUtil.flash(redirectAttributes, "success", "signupSuccess");
		} 
		catch (Exception  e) {
			logger.error(pagoGradoRelForm.toString()+e.getMessage());
			MyUtil.flash(redirectAttributes, "danger", "pagoNoSuccess", e.getMessage());
			e.printStackTrace();
		}
		

		return "redirect:/pagoGrado";
	}

}
