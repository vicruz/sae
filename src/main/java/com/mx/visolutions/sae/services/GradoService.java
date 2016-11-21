package com.mx.visolutions.sae.services;

import java.util.List;

import com.mx.visolutions.sae.entities.Grado;

public interface GradoService {

	public List<Grado> findAll();
	public  Grado findByName(String name);
}
