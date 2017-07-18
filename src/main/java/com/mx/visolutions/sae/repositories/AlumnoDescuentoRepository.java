package com.mx.visolutions.sae.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.visolutions.sae.entities.AlumnoDescuento;

public interface AlumnoDescuentoRepository extends JpaRepository<AlumnoDescuento,Integer>{

	List<AlumnoDescuento> findByIdAlumno(Integer IdAlumno);
}
