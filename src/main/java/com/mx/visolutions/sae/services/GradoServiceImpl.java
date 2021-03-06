package com.mx.visolutions.sae.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mx.visolutions.sae.dto.GradoForm;
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
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public GradoForm save(GradoForm gradoForm) throws Exception {
		Grado grado = new Grado();
		
		//grado.setId(gradoForm.getGradoId());
		grado.setName(gradoForm.getNombre());
		
		grado = gradoRepository.save(grado);
		
		gradoForm.setGradoId(grado.getId());
		return gradoForm;
	}

	@Override
	public GradoForm findById(Integer gradoId) {
		GradoForm form = null;
		
		Grado grado = gradoRepository.findOne(gradoId);
		
		if(grado!=null){
			form = new GradoForm();
			form.setGradoId(gradoId);
			form.setNombre(grado.getName());
		}
		
		return form;
	}

	@Override
	public void update(GradoForm gradoForm) {
		
		Grado grado = gradoRepository.findOne(gradoForm.getGradoId());
		grado.setName(gradoForm.getNombre());
		
		gradoRepository.save(grado);
	}
	
	
	
	
}
