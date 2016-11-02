package com.mx.visolutions.sae.dto;

import java.util.Date;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class AlumnoPagoForm {

	private Integer id;
	private Integer idConcepto;
	private Integer idAlumno;
	private Integer idPagoGrado;
	private String concepto;
	
	@NumberFormat(style = Style.NUMBER, pattern = "$#,#00.00")
	private Double monto;
	
	@NumberFormat(style = Style.NUMBER, pattern = "$#,#00.00")
	private Double pago;
	private Date fechaPago;
	public String semaforo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdConcepto() {
		return idConcepto;
	}
	public void setIdConcepto(Integer idConcepto) {
		this.idConcepto = idConcepto;
	}
	public Integer getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(Integer idAlumno) {
		this.idAlumno = idAlumno;
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
	public Double getPago() {
		return pago;
	}
	public void setPago(Double pago) {
		this.pago = pago;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Integer getIdPagoGrado() {
		return idPagoGrado;
	}
	public void setIdPagoGrado(Integer idPagoGrado) {
		this.idPagoGrado = idPagoGrado;
	}
	public String getSemaforo() {
		return semaforo;
	}
	public void setSemaforo(String semaforo) {
		this.semaforo = semaforo;
	}
	
	@Override
	public String toString() {
		return "AlumnoPagoForm [id=" + id + ", idConcepto=" + idConcepto + ", idAlumno=" + idAlumno + ", idPagoGrado="
				+ idPagoGrado + ", concepto=" + concepto + ", monto=" + monto + ", pago=" + pago + ", fechaPago="
				+ fechaPago + ", semaforo=" + semaforo + "]";
	}
	
}
