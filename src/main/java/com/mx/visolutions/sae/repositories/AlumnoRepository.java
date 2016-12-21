package com.mx.visolutions.sae.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.visolutions.sae.entities.Alumno;
import com.mx.visolutions.sae.entities.Grado;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

	List<Alumno> findByGrado(Grado grado);
}
