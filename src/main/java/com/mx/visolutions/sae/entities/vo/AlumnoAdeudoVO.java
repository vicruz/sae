package com.mx.visolutions.sae.entities.vo;

import java.util.Date;

import com.mx.visolutions.sae.util.Constantes;

public class AlumnoAdeudoVO {

	private Integer idPago;
	private String concepto;
	private Integer anio;
	private Integer mes;
	private String mesDescripcion;
	private Double monto;
	private Double pago;
	private Date fechaLimite;
	private Integer idSemaforo;
	private String semaforoDescripcion;
	
	public AlumnoAdeudoVO(Integer idPago, String concepto, Integer anio, Integer mes, String mesDescripcion,
			Double monto, Double pago, Date fechaLimite, Integer idSemaforo, String semaforoDescripcion) {
		super();
		this.idPago = idPago;
		this.concepto = concepto;
		this.anio = anio;
		this.mes = mes;
		this.mesDescripcion = mesDescripcion;
		this.monto = monto;
		this.pago = pago;
		this.fechaLimite = fechaLimite;
		this.idSemaforo = idSemaforo;
		this.semaforoDescripcion = semaforoDescripcion;
	}
	
	public AlumnoAdeudoVO(Integer idPago, String concepto, Integer anio, Integer mes,
			Double monto, Double pago, Date fechaLimite, Integer idSemaforo) {
		super();
		this.idPago = idPago;
		this.concepto = concepto;
		this.anio = anio;
		this.mes = mes;
		this.monto = monto;
		this.pago = pago;
		this.fechaLimite = fechaLimite;
		this.idSemaforo = idSemaforo;
	}
	
	public Integer getIdPago() {
		return idPago;
	}
	public void setIdPago(Integer idPago) {
		this.idPago = idPago;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public Integer getMes() {
		return mes;
	}
	public void setMes(Integer mes) {
		this.mes = mes;
	}
	public String getMesDescripcion() {
		if(mesDescripcion==null){
			switch(mes){
				case 1:
					mesDescripcion = "ENERO";
					break;
				case 2:
					mesDescripcion = "FEBRERO";
					break;
				case 3:
					mesDescripcion = "MARZO";
					break;
				case 4:
					mesDescripcion = "ABRIL";
					break;
				case 5:
					mesDescripcion = "MAYO";
					break;
				case 6:
					mesDescripcion = "JUNIO";
					break;
				case 7:
					mesDescripcion = "JULIO";
					break;
				case 8:
					mesDescripcion = "AGOSTO";
					break;
				case 9:
					mesDescripcion = "SEPTIEMBRE";
					break;
				case 10:
					mesDescripcion = "OCTUBRE";
					break;
				case 11:
					mesDescripcion = "NOVIEMBRE";
					break;
				default:
					mesDescripcion = "DICIEMBRE";
					break;
			}
		}
		return mesDescripcion;
	}
	public void setMesDescripcion(String mesDescripcion) {
		this.mesDescripcion = mesDescripcion;
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
	public Date getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	public Integer getIdSemaforo() {
		return idSemaforo;
	}
	public void setIdSemaforo(Integer idSemaforo) {
		this.idSemaforo = idSemaforo;
	}
	public String getSemaforoDescripcion() {
		if(semaforoDescripcion==null){
			switch (idSemaforo){
			case 1:
				semaforoDescripcion = "PAGADO";
				break;
			case 2:
				semaforoDescripcion = "PARCIAL";
				break;
			case 3:
				semaforoDescripcion = "ADEUDO";
				break;
			default:
				semaforoDescripcion = "PENDIENTE";
				break;
			}
				
		}
		return semaforoDescripcion;
	}
	public void setSemaforoDescripcion(String semaforoDescripcion) {
		this.semaforoDescripcion = semaforoDescripcion;
	}

	@Override
	public String toString() {
		return "AlumnoAdeudoVO [idPago=" + idPago + ", concepto=" + concepto + ", anio=" + anio + ", mes=" + mes
				+ ", mesDescripcion=" + mesDescripcion + ", monto=" + monto + ", pago=" + pago + ", fechaLimite="
				+ fechaLimite + ", idSemaforo=" + idSemaforo + ", semaforoDescripcion=" + semaforoDescripcion + "]";
	}
	
}
