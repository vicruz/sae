package com.mx.visolutions.sae.dto;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class CatPagosForm {
	
	private String concepto;
	
	@NumberFormat(style = Style.NUMBER, pattern = "$#,#00.00")
	private Double monto;

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}
	
	

	
}
