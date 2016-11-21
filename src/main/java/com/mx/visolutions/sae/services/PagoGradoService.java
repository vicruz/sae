package com.mx.visolutions.sae.services;

import java.util.List;

import com.mx.visolutions.sae.dto.PagoGradoRelForm;
import com.mx.visolutions.sae.entities.PagoGrado;

public interface PagoGradoService {

	public List<PagoGrado> getByIdGrado(Integer idGrado);
	
	public List<PagoGrado> findAll();
	
	public void addNew(PagoGradoRelForm pagoGradoRelForm) throws Throwable;
}
