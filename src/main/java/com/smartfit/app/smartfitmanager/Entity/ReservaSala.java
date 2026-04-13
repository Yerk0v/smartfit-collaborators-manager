package com.smartfit.app.smartfitmanager.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservas_sala")
public class ReservaSala {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombreSolicitante;

	@Column(nullable = false)
	private String apellidoSolicitante;

	@Column(nullable = false)
	private LocalDate fecha;

	@Column(nullable = false)
	private LocalTime horaInicio;

	@Column(nullable = false)
	private LocalTime horaFin;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "sala_id", nullable = false)
	private Sala sala;

	public ReservaSala() {
	}

	public ReservaSala(
			String nombreSolicitante,
			String apellidoSolicitante,
			LocalDate fecha,
			LocalTime horaInicio,
			LocalTime horaFin,
			Sala sala) {
		this.nombreSolicitante = nombreSolicitante;
		this.apellidoSolicitante = apellidoSolicitante;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.sala = sala;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
}
