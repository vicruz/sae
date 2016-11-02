package com.mx.visolutions.sae.services;

import java.util.List;

import com.mx.visolutions.sae.dto.AlumnoPagoForm;
import com.mx.visolutions.sae.entities.AlumnoPago;

public interface AlumnoPagoService {

	public List<AlumnoPago> findAll();
	
	public void save(AlumnoPagoForm alumnoForm) throws Exception;
	
	public void update(AlumnoPagoForm alumnoForm);
	
	public List<AlumnoPagoForm> findByIdAlumno(Integer id);
	
	public void delete(AlumnoPagoForm alumnoForm);
	
	public void getList();
	
}