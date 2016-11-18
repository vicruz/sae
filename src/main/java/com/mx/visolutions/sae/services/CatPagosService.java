package com.mx.visolutions.sae.services;

import java.util.List;

import com.mx.visolutions.sae.dto.CatPagosForm;
import com.mx.visolutions.sae.entities.CatPagos;

public interface CatPagosService {
	
	public CatPagos findById(Integer id); 
	public CatPagos findByPagoGradoId(Integer id);
	public List<CatPagos> findAll();
	public void addNuevoPago(CatPagosForm catPagosForm);
	
	public int deleteCatPago(int pagoId);
	
	
	
	
}
