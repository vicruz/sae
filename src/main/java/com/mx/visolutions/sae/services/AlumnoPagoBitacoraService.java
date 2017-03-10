package com.mx.visolutions.sae.services;

import java.util.Date;
import java.util.List;

import com.mx.visolutions.sae.entities.AlumnoReportDailyVO;

public interface AlumnoPagoBitacoraService {

	List<AlumnoReportDailyVO> getPagosBetweenFechaPago(Date fechaInicio, Date fechaFin);
	
}
