package com.mx.visolutions.sae.services;

import com.mx.visolutions.sae.entities.CatPagos;

public interface CatPagosService {
	
	CatPagos findById(Integer id); 
	CatPagos findByPagoGradoId(Integer id);
}
