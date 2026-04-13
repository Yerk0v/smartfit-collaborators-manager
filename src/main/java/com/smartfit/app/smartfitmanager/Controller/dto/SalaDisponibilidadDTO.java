package com.smartfit.app.smartfitmanager.Controller.dto;

import java.util.List;

public class SalaDisponibilidadDTO {

	private final Long id;
	private final String nombre;
	private final String tipo;
	private final Integer aforo;
	private final String descripcion;
	private final boolean disponibleAhora;
	private final String estadoActual;
	private final String proximaReserva;
	private final List<String> equipamientos;

	public SalaDisponibilidadDTO(
			Long id,
			String nombre,
			String tipo,
			Integer aforo,
			String descripcion,
			boolean disponibleAhora,
			String estadoActual,
			String proximaReserva,
			List<String> equipamientos) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.aforo = aforo;
		this.descripcion = descripcion;
		this.disponibleAhora = disponibleAhora;
		this.estadoActual = estadoActual;
		this.proximaReserva = proximaReserva;
		this.equipamientos = equipamientos;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public Integer getAforo() {
		return aforo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean isDisponibleAhora() {
		return disponibleAhora;
	}

	public String getEstadoActual() {
		return estadoActual;
	}

	public String getProximaReserva() {
		return proximaReserva;
	}

	public List<String> getEquipamientos() {
		return equipamientos;
	}
}
