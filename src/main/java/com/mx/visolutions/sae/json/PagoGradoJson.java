package com.mx.visolutions.sae.json;

import java.io.Serializable;

public class PagoGradoJson implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idPagoGrado;
	private String concepto;
	private Double monto;
	
	public Integer getIdPagoGrado() {
		return idPagoGrado;
	}
	public void setIdPagoGrado(Integer idPagoGrado) {
		this.idPagoGrado = idPagoGrado;
	}
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
