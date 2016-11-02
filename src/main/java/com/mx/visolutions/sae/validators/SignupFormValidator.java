package com.mx.visolutions.sae.validators;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.mx.visolutions.sae.dto.SignupForm;
import com.mx.visolutions.sae.entities.User;
import com.mx.visolutions.sae.repositories.UserRepository;

@Component
public class SignupFormValidator extends LocalValidatorFactoryBean{
	
	private UserRepository userRepository;
	
	@Resource
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(SignupForm.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors, Object... validationHints) {
		super.validate(obj, errors, validationHints);
		
		if(!errors.hasErrors()){
			SignupForm signupForm = (SignupForm)obj;
			User user = userRepository.findByUsuario(signupForm.getName());
			if(user!=null){
				errors.reject("name","usuarioNoUnico");
			}
		}
		
	}

}
