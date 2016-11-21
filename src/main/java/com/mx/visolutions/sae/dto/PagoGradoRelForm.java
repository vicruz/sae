package com.mx.visolutions.sae.dto;



import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class PagoGradoRelForm {

	private Integer idGrado;
	private Integer idPago;
	
	private String fechaLimite;
	
	private String mes;
	
	private String anio;

	@NumberFormat(style = Style.NUMBER, pattern = "$#,#00.00")
	private Double monto;
	
	
	public String getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(String fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	
	
	public Integer getIdGrado() {
		return idGrado;
	}
	public void setIdGrado(Integer idGrado) {
		this.idGrado = idGrado;
	}
	public Integer getIdPago() {
		return idPago;
	}
	public void setIdPago(Integer idPago) {
		this.idPago = idPago;
	}
	
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	@Override
	public String toString() {
		return "PagoGradoRelForm [idGrado=" + idGrado + ", idPago=" + idPago + ", fechaLimite=" + fechaLimite + ", Mes="
				+ mes + ", anio=" + anio + ", monto=" + monto +"]";
	}
	
	
}