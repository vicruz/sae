package com.mx.visolutions.sae.controller;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mx.visolutions.sae.dto.CatPagosForm;
import com.mx.visolutions.sae.entities.CatPagos;
import com.mx.visolutions.sae.services.CatPagosService;
import com.mx.visolutions.sae.util.MyUtil;

import javax.validation.Valid;

@Controller
public class PagosController {

	private static final Logger logger = LoggerFactory.getLogger(PagosController.class);

	private CatPagosService catPagosService;

	@Autowired
	public PagosController(CatPagosService catPagosService) {
		this.catPagosService = catPagosService;
	}

	@RequestMapping(value = "/getPagos", method = RequestMethod.GET)
	public String administrador(Model model) {

		model.addAttribute(new CatPagosForm());
		return "catalogoPagos";
	}

	@RequestMapping(value = "/getPagos", method = RequestMethod.POST)
	public String signup(@ModelAttribute("signupForm") @Valid CatPagosForm catPagosForm, BindingResult result,
			RedirectAttributes redirectAttributes) {
		System.out.println("User controller" + catPagosForm.getConcepto());
		if (result.hasErrors())
			return "usuario";

		logger.info(catPagosForm.toString());

		try {
			catPagosService.addNuevoPago(catPagosForm);
			MyUtil.flash(redirectAttributes, "success", "signupSuccess");
		} catch (Exception e) {

			MyUtil.flashNotProperties(redirectAttributes, "danger", e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/getPagos";
	}

	@RequestMapping(value = "/borrar/{usuarioId}", method = RequestMethod.GET)
	public String borrarUsuario(@PathVariable("usuarioId") Integer pagoId, Model model,
			RedirectAttributes redirectAttributes) {
		System.out.println("dele controller");
		try {

			CatPagos catPagoBusqueda = catPagosService.findById(pagoId);

			if (catPagoBusqueda != null) {
				catPagosService.deleteCatPago(pagoId);
				MyUtil.flash(redirectAttributes, "success", "signupSuccess");
			}

		} catch (Exception e) {
			MyUtil.flashNotProperties(redirectAttributes, "danger", e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/getPagos";
	}

}
