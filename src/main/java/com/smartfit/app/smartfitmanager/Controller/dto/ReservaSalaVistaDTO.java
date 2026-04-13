package com.smartfit.app.smartfitmanager.Controller.dto;

public class ReservaSalaVistaDTO {

	private final String salaNombre;
	private final String solicitante;
	private final String fecha;
	private final String horario;

	public ReservaSalaVistaDTO(String salaNombre, String solicitante, String fecha, String horario) {
		this.salaNombre = salaNombre;
		this.solicitante = solicitante;
		this.fecha = fecha;
		this.horario = horario;
	}

	public String getSalaNombre() {
		return salaNombre;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public String getFecha() {
		return fecha;
	}

	public String getHorario() {
		return horario;
	}
}
