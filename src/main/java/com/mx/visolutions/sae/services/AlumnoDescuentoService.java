package com.mx.visolutions.sae.services;

import java.text.ParseException;
import java.util.List;

import com.mx.visolutions.sae.dto.DescuentoForm;

public interface AlumnoDescuentoService {
	
	public void save(DescuentoForm descuentoform, Integer idAlumno) throws ParseException;
	
	public DescuentoForm findOne(Integer idBeca);
	
	public List<DescuentoForm> findListAlumno(Integer idAlumno);

}
