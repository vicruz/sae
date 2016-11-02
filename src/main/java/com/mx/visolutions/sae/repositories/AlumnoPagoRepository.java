package com.mx.visolutions.sae.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.visolutions.sae.entities.AlumnoPago;

public interface AlumnoPagoRepository extends JpaRepository<AlumnoPago, Integer>{

	List<AlumnoPago> findByIdAlumno(Integer idAlumno);
}
