package com.mx.visolutions.sae.services;

import java.util.List;

import com.mx.visolutions.sae.dto.AlumnoForm;
import com.mx.visolutions.sae.entities.Alumno;

public interface AlumnoService {

	public List<Alumno> findAll();
	
	public void save(AlumnoForm alumnoForm) throws Exception;
	
	public void update(AlumnoForm alumnoForm);
	
	public Alumno findById(Integer id);
	
	public void delete(AlumnoForm alumnoForm);
	
	public void getList();
	
	public List<Alumno> findByGrado(Integer idGrado);
}
