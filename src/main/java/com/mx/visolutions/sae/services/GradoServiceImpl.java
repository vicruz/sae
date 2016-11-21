package com.mx.visolutions.sae.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mx.visolutions.sae.entities.Grado;
import com.mx.visolutions.sae.repositories.GradoRepository;


@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class GradoServiceImpl implements GradoService{

	private GradoRepository gradoRepository;
	
	@Autowired
	public GradoServiceImpl(GradoRepository gradoRepository){
		this.gradoRepository = gradoRepository;
		
	}

	@Override
	public List<Grado> findAll() {
		
		return gradoRepository.findAll();
	}

	@Override
	public Grado findByName(String name) {
		
		return gradoRepository.findByName(name);
	}
	
	
	
	
}
