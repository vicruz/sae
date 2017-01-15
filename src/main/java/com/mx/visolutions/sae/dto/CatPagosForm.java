package com.mx.visolutions.sae.dto;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class CatPagosForm {
	
	private Integer id;
	
	private String concepto;
	
	@NumberFormat(style = Style.NUMBER, pattern = "$#,#00.00")
	private Double monto;
	
	private Boolean beca;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public Boolean getBeca() {
		return beca;
	}

	public void setBeca(Boolean beca) {
		this.beca = beca;
	}

	@Override
	public String toString() {
		return "CatPagoForm [concepto=" + concepto + ", monto=" + monto + ", beca="+beca+"]";
	}
	

	
}
