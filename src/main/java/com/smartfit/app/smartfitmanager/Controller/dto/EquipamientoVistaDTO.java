package com.smartfit.app.smartfitmanager.Controller.dto;

public class EquipamientoVistaDTO {

	private final String salaNombre;
	private final String nombre;
	private final String categoria;
	private final Integer cantidad;

	public EquipamientoVistaDTO(String salaNombre, String nombre, String categoria, Integer cantidad) {
		this.salaNombre = salaNombre;
		this.nombre = nombre;
		this.categoria = categoria;
		this.cantidad = cantidad;
	}

	public String getSalaNombre() {
		return salaNombre;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public Integer getCantidad() {
		return cantidad;
	}
}
