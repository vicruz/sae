package com.mx.visolutions.sae.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.visolutions.sae.entities.Grado;

public interface GradoRepository extends JpaRepository<Grado, Integer> {


	@Query("Select gd From Grado gd WHERE gd.name = :name")
	public Grado findByName(@Param("name") String name);
	
}
