package com.mx.visolutions.sae.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mx.visolutions.sae.dto.AlumnoForm;
import com.mx.visolutions.sae.entities.Alumno;
import com.mx.visolutions.sae.entities.Grado;
import com.mx.visolutions.sae.repositories.AlumnoRepository;


@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class AlumnoServiceImpl implements AlumnoService {
	
	private AlumnoRepository alumnoRepository;
	
	@Autowired
	private AlumnoServiceImpl(AlumnoRepository alumnoRepository){
		this.alumnoRepository =alumnoRepository;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void save(AlumnoForm alumnoForm) throws Exception {
		Alumno alumno = new Alumno();
		Grado grado = new Grado();
		grado.setId(alumnoForm.getGradoId());
		alumno.setApMaterno(alumnoForm.getApMaterno());
		alumno.setApPaterno(alumnoForm.getApPaterno());
		alumno.setNombre(alumnoForm.getNombre());
		alumno.setGrado(grado);
		if(alumnoForm.getBecaId())
			alumno.setBeca(1);
		else
			alumno.setBeca(0);
		alumnoRepository.save(alumno);
	}

	@Override
	public void update(AlumnoForm alumnoForm) {
		Alumno alumno = alumnoRepository.findOne(alumnoForm.getId());
		Grado grado = new Grado();
		
		alumno.setApMaterno(alumnoForm.getApMaterno());
		alumno.setApPaterno(alumnoForm.getApPaterno());
		alumno.setNombre(alumnoForm.getNombre());
		grado.setId(alumnoForm.getGradoId());
		alumno.setGrado(grado);
		
		alumnoRepository.save(alumno);
	}

	@Override
	public Alumno findById(Integer id) {
		return alumnoRepository.findOne(id);
	}

	@Override
	public void delete(AlumnoForm alumnoForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Alumno> findAll() {
		
		return alumnoRepository.findAll();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public List<Alumno> findByGrado(Integer idGrado) {
		Grado grado = new Grado();
		grado.setId(idGrado);
		return alumnoRepository.findByGrado(grado);
	}

	

}
