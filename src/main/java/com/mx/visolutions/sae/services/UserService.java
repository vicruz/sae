package com.mx.visolutions.sae.services;

import com.mx.visolutions.sae.dto.SignupForm;
import com.mx.visolutions.sae.dto.UserForm;
import com.mx.visolutions.sae.entities.User;

public interface UserService {

	public abstract void signup(SignupForm signupForm);
	public abstract void newUser(UserForm userForm);
	public User findUserById(int usuarioId);
	public int  deleteUserById(int usuarioId);
	
}
