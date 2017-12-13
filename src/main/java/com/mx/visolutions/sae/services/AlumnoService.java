package com.mx.visolutions.sae.services;

import java.util.List;

import com.mx.visolutions.sae.dto.AlumnoForm;
import com.mx.visolutions.sae.entities.Alumno;
import com.mx.visolutions.sae.json.AlumnoJson;
import com.mx.visolutions.sae.json.JSon;

public interface AlumnoService {

	public List<Alumno> findAll();
	
	public void save(AlumnoForm alumnoForm) throws Exception;
	
	public void update(AlumnoForm alumnoForm);
	
	public Alumno findById(Integer id);
	
	public void delete(AlumnoForm alumnoForm);
	
	public JSon getList();
	
	public List<Alumno> findByGrado(Integer idGrado);
	
	public void deactivateAllAlumnos();
	
	public void activateAllAlumnos();
	
	public void changeEstatusByAlumno(int activo, int alumnoId);
	
	public List<Alumno> findByGradoAndActivo(Integer idGrado);
	
	public List<AlumnoJson> findActivos();
}
