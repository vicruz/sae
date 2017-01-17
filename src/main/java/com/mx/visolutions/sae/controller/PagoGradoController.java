/**
 * 
 */
package com.mx.visolutions.sae.controller;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

import com.mx.visolutions.sae.dto.AlumnoPagoForm;
//import com.mx.visolutions.sae.dto.AlumnoPagoForm;
import com.mx.visolutions.sae.dto.PagoGradoRelForm;
import com.mx.visolutions.sae.entities.Alumno;
import com.mx.visolutions.sae.entities.CatPagos;
import com.mx.visolutions.sae.entities.Grado;
import com.mx.visolutions.sae.entities.PagoGrado;
import com.mx.visolutions.sae.services.AlumnoPagoService;
import com.mx.visolutions.sae.services.AlumnoService;
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
	private AlumnoService alumnoService; 
	private AlumnoPagoService alumnoPagoService;
	
	private static final Logger logger = LoggerFactory.getLogger(PagoGradoController.class);

	@Autowired
	public PagoGradoController(UserService userService,PagoGradoService pagoGradoService,
			AlumnoPagoService alumnoPagoService, CatPagosService catPagosService,GradoService gradoService, 
			AlumnoService alumnoService) {
		this.pagoGradoService = pagoGradoService;
		this.catPagosService = catPagosService;
		this.gradoService	= gradoService;
		this.alumnoService = alumnoService;
		this.alumnoPagoService = alumnoPagoService;
	}

	@RequestMapping(value="/pagoGrado", method=RequestMethod.GET)
	public String pagoGrado(Model model){
		logger.info("Busca los id pago-catalogo pago");

		List<Grado> grado;
		List<CatPagos> catPagos;
		Map<String,String> map = new HashMap<String,String>();
		Map<String,String> map2 = new HashMap<String,String>();
		Map<Integer,String> mesesMap = new HashMap<Integer,String>();

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
		mesesMap.put(1, "Enero");
		mesesMap.put(2, "Febrero");
		mesesMap.put(3, "Marzo");
		mesesMap.put(4, "Abril");
		mesesMap.put(5, "Mayo");
		mesesMap.put(6, "Junio");
		mesesMap.put(7, "Julio");
		mesesMap.put(8, "Agosto");
		mesesMap.put(9, "Septiembre");
		mesesMap.put(10, "Octubre");
		mesesMap.put(11, "Noviembre");
		mesesMap.put(12, "Diciembre");
				 
		 Map<Integer, String> treeMap = new TreeMap<Integer, String>(
	                new Comparator<Integer>() {

	                    @Override
	                    public int compare(Integer o1, Integer o2) {
	                        return o1.compareTo(o2);
	                    }

	                });   
		 
		 treeMap.putAll(mesesMap);
		model.addAttribute("grados", map);
		model.addAttribute("conceptos", map2);
		model.addAttribute("meses", treeMap);

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
		
		List<Alumno> alumnos;
		
		if((aniolim<anio)|| (mes>12)||(dia>31)){
			MyUtil.flash(redirectAttributes, "danger", "pagoGradoNoSuccess");
			return "redirect:/pagoGrado";
		}
			
		if(result.hasErrors())
			return "redirect:/pagoGrado";
		
		try {
			//Se agrega el pago a la relación de pagos por grado
			PagoGrado pagoGrado = pagoGradoService.addNew(pagoGradoRelForm);
			
			///////////////////////////////////////////////////////
			//Se agrega el pago a los alumnos inscritos en el grado
			
			//Se obtienen los alumnos en el grado
			alumnos = alumnoService.findByGrado(pagoGradoRelForm.getIdGrado());
			
			//Se obtiene el monto de pago del concepto
			CatPagos catPago = catPagosService.findById(pagoGradoRelForm.getIdPago());
			
			//Se realiza un ciclo para agregar el pago a cada alumno
			for(Alumno alumno: alumnos){
				AlumnoPagoForm alumnoForm = new AlumnoPagoForm();
				
				alumnoForm.setIdPagoGrado(pagoGrado.getId());
				alumnoForm.setIdAlumno(alumno.getId());
				alumnoForm.setMonto(catPago.getMonto());
				alumnoForm.setFechaPago(null);
				alumnoForm.setPago(0.0);
				alumnoForm.setFechaLimite(pagoGradoRelForm.getFechaLimite());
				alumnoPagoService.save(alumnoForm);
			}
			
			MyUtil.flash(redirectAttributes, "success", "pagoGradoSuccess");
		} 
		catch (Exception  e) {
			logger.error(pagoGradoRelForm.toString()+e.getMessage());
			MyUtil.flash(redirectAttributes, "danger", "pagoGradoNoSuccess", e.getMessage());
			e.printStackTrace();
		}
		

		return "redirect:/pagoGrado";
	}

}
