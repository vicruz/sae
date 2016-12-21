package com.mx.visolutions.sae.services;

import java.util.Date;

import com.mx.visolutions.sae.json.EstadisticaJson;

public interface EstadisticaService {

	EstadisticaJson currentMonthPays(boolean total);
	
	EstadisticaJson betweentMonthPays(Date dateInicio, Date dateFinal, boolean total);
	
}
