package com.mx.visolutions.sae.dto;

import javax.validation.constraints.NotNull;

public class UserForm {
	
	@NotNull
	private String usuario;
	
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String userName) {
		this.usuario = userName;
	}

}
