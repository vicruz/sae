package com.mx.visolutions.sae.services;

import java.util.List;

import com.mx.visolutions.sae.dto.BecaForm;

public interface AlumnoBecaService {
	
	public void save(BecaForm becaform, Integer idAlumno);
	
	public BecaForm findOne(Integer idBeca);
	
	public List<BecaForm> findListAlumno(Integer idAlumno);
	
	public BecaForm findByAlumnoAndCurrentDate(Integer idAlumno);

}
