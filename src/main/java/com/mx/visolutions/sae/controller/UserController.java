package com.mx.visolutions.sae.controller;

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

import com.mx.visolutions.sae.dto.UserForm;
import com.mx.visolutions.sae.entities.User;
import com.mx.visolutions.sae.services.AlumnoService;
import com.mx.visolutions.sae.services.UserService;
import com.mx.visolutions.sae.util.MyUtil;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService userService;

	@Autowired
	public UserController(UserService userService,AlumnoService alumnoService) {
		this.userService = userService;

	}
	@RequestMapping(value="/usuario" ,method=RequestMethod.GET)
	public String usuario(Model model){
		model.addAttribute(new  UserForm());
		return "usuario";
	}


	@RequestMapping(value="/usuario", method = RequestMethod.POST)
	public String signup(@ModelAttribute("signupForm") @Valid UserForm userForm,
			BindingResult result, RedirectAttributes redirectAttributes){
		if(result.hasErrors()){
			return "usuario";
		}

		logger.info("Sigup-Post"+userForm.toString());

		try {
			userService.newUser(userForm);
			MyUtil.flash(redirectAttributes, "success", "signupSuccess");
		} catch (Exception e) {
			MyUtil.flashNotProperties(redirectAttributes, "danger", e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/usuario";
	}


	@RequestMapping(value="/usuario/{usuarioId}", method=RequestMethod.GET)
	public String borrarUsuario(@PathVariable("usuarioId") Integer usuarioId, Model model
			,RedirectAttributes redirectAttributes){
		try {
			User usuarioBusqueda=userService.findUserById(usuarioId);
			if(usuarioBusqueda!=null){
				userService.deleteUserById(usuarioId);
				MyUtil.flash(redirectAttributes, "success", "signupSuccess");
			}
					
		} catch (Exception e) {
			//TODO Cambiar el label
			MyUtil.flashNotProperties(redirectAttributes, "danger", e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/usuario";
	}

}
