package com.mx.visolutions.sae.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mx.visolutions.sae.entities.AlumnoPago;

public interface AlumnoPagoRepository extends JpaRepository<AlumnoPago, Integer>{

	List<AlumnoPago> findByIdAlumno(Integer idAlumno);
	
	/*
	select pg.id, ap.monto, ap.pago
	from sae.pago_grado pg
	join sae.alumno_pago ap on pg.id = ap.id_pago_grado
	where pg.mes_corresponde = 12 and pg.anio_corresponde = 2016;
	*/
	@Query("Select ap from AlumnoPago ap where ap.pagoGrado.mes_corresponde = ?1 and ap.pagoGrado.anio_corresponde = ?2")
	List<AlumnoPago> currentMonthPays(Integer mes, Integer anio);
	
	/*
	select pg.id, ap.monto, ap.pago
	from sae.pago_grado pg
	join sae.alumno_pago ap on pg.id = ap.id_pago_grado
	where (pg.mes_corresponde >= 12 and pg.mes_corresponde <= 12)
	and (pg.anio_corresponde >= 2016 and pg.anio_corresponde <= 2016);
	*/
	@Query("Select ap from AlumnoPago ap where ap.pagoGrado.mes_corresponde between ?1 and ?2 "
			+ "and ap.pagoGrado.anio_corresponde between ?3 and ?4")
	List<AlumnoPago> betweenMonthPays(Integer mesIni, Integer mesFin, Integer anioIni, Integer anioFin);
	
	
	
}
