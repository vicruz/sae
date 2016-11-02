package com.mx.visolutions.sae.json;

import java.io.Serializable;
import java.util.Date;

public class AlumnoPagoJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String concepto;
	private Double monto;
	private Double pago;
	private String fecha;
	private String estatus;
	
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
	public Double getPago() {
		return pago;
	}
	public void setPago(Double pago) {
		this.pago = pago;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	
}
