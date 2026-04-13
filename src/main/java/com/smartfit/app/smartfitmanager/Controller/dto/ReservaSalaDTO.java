package com.smartfit.app.smartfitmanager.Controller.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ReservaSalaDTO {

	private String nombreSolicitante;
	private String apellidoSolicitante;
	private Long salaId;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate fecha;

	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime horaInicio;

	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime horaFin;

	public String getNombreSolicitante() {
		return nombreSolicitante;
	}

	public void setNombreSolicitante(String nombreSolicitante) {
		this.nombreSolicitante = nombreSolicitante;
	}

	public String getApellidoSolicitante() {
		return apellidoSolicitante;
	}

	public void setApellidoSolicitante(String apellidoSolicitante) {
		this.apellidoSolicitante = apellidoSolicitante;
	}

	public Long getSalaId() {
		return salaId;
	}

	public void setSalaId(Long salaId) {
		this.salaId = salaId;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(LocalTime horaFin) {
		this.horaFin = horaFin;
	}
}
