package com.mx.visolutions.sae.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mx.visolutions.sae.entities.CatPagos;

public interface CatPagosRepository extends JpaRepository<CatPagos, Integer>{

	@Modifying
	@Query("Delete From CatPagos pa Where pa.id = :pagoId")
	int deleteById(@Param("pagoId")int pagoId);
	
}
