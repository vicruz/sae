package com.mx.visolutions.sae.services;

import java.util.List;

import com.mx.visolutions.sae.dto.GradoForm;
import com.mx.visolutions.sae.entities.Grado;

public interface GradoService {

	public List<Grado> findAll();
	public Grado findByName(String name);
	public GradoForm save(GradoForm gradoForm) throws Exception;
	public GradoForm findById(Integer gradoId);
	public void update(GradoForm gradoForm);
}
