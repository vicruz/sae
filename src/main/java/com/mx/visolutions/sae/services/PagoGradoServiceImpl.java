package com.mx.visolutions.sae.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mx.visolutions.sae.entities.PagoGrado;
import com.mx.visolutions.sae.repositories.PagoGradoRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class PagoGradoServiceImpl implements PagoGradoService {
	
	private PagoGradoRepository pagoGradoRepository;
	
	@Autowired
	public PagoGradoServiceImpl(PagoGradoRepository pagoGradoRepository){
		this.pagoGradoRepository = pagoGradoRepository;
	}

	@Override
	public List<PagoGrado> getByIdGrado(Integer idGrado) {
		return  pagoGradoRepository.findByIdGrado(idGrado);
	}

}
