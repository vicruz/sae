package com.mx.visolutions.sae.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.visolutions.sae.entities.PagoGrado;

public interface PagoGradoRepository extends JpaRepository<PagoGrado, Integer>{

	List<PagoGrado> findByIdGrado(Integer idGrado);
}
