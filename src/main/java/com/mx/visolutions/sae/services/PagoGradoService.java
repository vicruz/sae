package com.mx.visolutions.sae.services;

import java.util.List;

import com.mx.visolutions.sae.dto.PagoGradoRelForm;
import com.mx.visolutions.sae.entities.PagoGrado;

public interface PagoGradoService {
	
	public PagoGrado findOne(Integer idPagoGrado);

	public List<PagoGrado> getByIdGrado(Integer idGrado);
	
	public List<PagoGrado> findAll();
	
	public PagoGrado addNew(PagoGradoRelForm pagoGradoRelForm) throws Throwable;
	
	public List<PagoGrado> findByIdGradoNotInAlumno(Integer idGrado, Integer idAlumno);
	
	public void save(PagoGrado pagoGrado);
	
	public void flush();
	
	public int findPagoMade(int idPagoGrado);
	
	public void delete(Integer idPagoGrado);
}
