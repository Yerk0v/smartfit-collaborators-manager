package com.smartfit.app.smartfitmanager.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartfit.app.smartfitmanager.Controller.dto.EquipamientoRegistroDTO;
import com.smartfit.app.smartfitmanager.Controller.dto.EquipamientoVistaDTO;
import com.smartfit.app.smartfitmanager.Controller.dto.ReservaSalaDTO;
import com.smartfit.app.smartfitmanager.Controller.dto.ReservaSalaVistaDTO;
import com.smartfit.app.smartfitmanager.Controller.dto.SalaDisponibilidadDTO;
import com.smartfit.app.smartfitmanager.Controller.dto.SalaRegistroDTO;
import com.smartfit.app.smartfitmanager.Entity.Equipamiento;
import com.smartfit.app.smartfitmanager.Entity.ReservaSala;
import com.smartfit.app.smartfitmanager.Entity.Sala;
import com.smartfit.app.smartfitmanager.Repository.EquipamientoRepository;
import com.smartfit.app.smartfitmanager.Repository.ReservaSalaRepository;
import com.smartfit.app.smartfitmanager.Repository.SalaRepository;

@Service
@Transactional
public class SalaServiceImpl implements SalaService {

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

	private final SalaRepository salaRepository;
	private final EquipamientoRepository equipamientoRepository;
	private final ReservaSalaRepository reservaSalaRepository;

	public SalaServiceImpl(
			SalaRepository salaRepository,
			EquipamientoRepository equipamientoRepository,
			ReservaSalaRepository reservaSalaRepository) {
		this.salaRepository = salaRepository;
		this.equipamientoRepository = equipamientoRepository;
		this.reservaSalaRepository = reservaSalaRepository;
	}

	@Override
	public void registrarSala(SalaRegistroDTO salaRegistroDTO) {
		String nombre = normalize(salaRegistroDTO.getNombre());
		String tipo = normalize(salaRegistroDTO.getTipo());
		String descripcion = normalizeNullable(salaRegistroDTO.getDescripcion());

		if (nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre de la sala es obligatorio.");
		}
		if (tipo.isEmpty()) {
			throw new IllegalArgumentException("Debes seleccionar un tipo de sala.");
		}
		if (salaRegistroDTO.getAforo() == null || salaRegistroDTO.getAforo() < 1) {
			throw new IllegalArgumentException("El aforo debe ser mayor a cero.");
		}
		if (salaRepository.existsByNombreIgnoreCase(nombre)) {
			throw new IllegalArgumentException("Ya existe una sala registrada con ese nombre.");
		}

		salaRepository.save(new Sala(nombre, tipo, salaRegistroDTO.getAforo(), descripcion));
	}

	@Override
	public void registrarEquipamiento(EquipamientoRegistroDTO equipamientoRegistroDTO) {
		String nombre = normalize(equipamientoRegistroDTO.getNombre());
		String categoria = normalize(equipamientoRegistroDTO.getCategoria());

		if (nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre del equipamiento es obligatorio.");
		}
		if (categoria.isEmpty()) {
			throw new IllegalArgumentException("La categoria del equipamiento es obligatoria.");
		}
		if (equipamientoRegistroDTO.getCantidad() == null || equipamientoRegistroDTO.getCantidad() < 1) {
			throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
		}

		Sala sala = obtenerSala(equipamientoRegistroDTO.getSalaId());
		equipamientoRepository.save(
				new Equipamiento(nombre, categoria, equipamientoRegistroDTO.getCantidad(), sala));
	}

	@Override
	public void reservarSala(ReservaSalaDTO reservaSalaDTO) {
		String nombre = normalize(reservaSalaDTO.getNombreSolicitante());
		String apellido = normalize(reservaSalaDTO.getApellidoSolicitante());

		if (nombre.isEmpty() || apellido.isEmpty()) {
			throw new IllegalArgumentException("El nombre y apellido del solicitante son obligatorios.");
		}
		if (reservaSalaDTO.getFecha() == null) {
			throw new IllegalArgumentException("Debes indicar la fecha de la reserva.");
		}
		if (reservaSalaDTO.getHoraInicio() == null || reservaSalaDTO.getHoraFin() == null) {
			throw new IllegalArgumentException("Debes indicar la hora de inicio y la hora de termino.");
		}
		if (!reservaSalaDTO.getHoraFin().isAfter(reservaSalaDTO.getHoraInicio())) {
			throw new IllegalArgumentException("La hora de termino debe ser posterior a la hora de inicio.");
		}
		if (reservaSalaDTO.getFecha().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("No puedes reservar salas en una fecha pasada.");
		}

		Sala sala = obtenerSala(reservaSalaDTO.getSalaId());
		boolean conflicto = reservaSalaRepository.existsBySalaAndFechaAndHoraInicioLessThanAndHoraFinGreaterThan(
				sala,
				reservaSalaDTO.getFecha(),
				reservaSalaDTO.getHoraFin(),
				reservaSalaDTO.getHoraInicio());

		if (conflicto) {
			throw new IllegalArgumentException("La sala ya tiene una reserva en ese rango horario.");
		}

		reservaSalaRepository.save(new ReservaSala(
				nombre,
				apellido,
				reservaSalaDTO.getFecha(),
				reservaSalaDTO.getHoraInicio(),
				reservaSalaDTO.getHoraFin(),
				sala));
	}

	@Override
	@Transactional(readOnly = true)
	public List<Sala> listarSalas() {
		return salaRepository.findAllByOrderByNombreAsc();
	}

	@Override
	@Transactional(readOnly = true)
	public List<SalaDisponibilidadDTO> listarDisponibilidadDeSalas() {
		LocalDate hoy = LocalDate.now();
		LocalTime ahora = LocalTime.now();

		return salaRepository.findAllByOrderByNombreAsc().stream().map(sala -> {
			List<Equipamiento> equipamientos = equipamientoRepository.findBySalaIdOrderByNombreAsc(sala.getId());
			List<ReservaSala> reservas = reservaSalaRepository.findBySalaOrderByFechaAscHoraInicioAsc(sala);

			boolean disponibleAhora = reservas.stream().noneMatch(reserva -> reserva.getFecha().equals(hoy)
					&& !reserva.getHoraInicio().isAfter(ahora)
					&& reserva.getHoraFin().isAfter(ahora));

			Optional<ReservaSala> proximaReserva = reservas.stream()
					.filter(reserva -> reserva.getFecha().isAfter(hoy)
							|| (reserva.getFecha().equals(hoy) && !reserva.getHoraFin().isBefore(ahora)))
					.findFirst();

			String estadoActual = disponibleAhora ? "Disponible ahora" : "Reservada en este momento";
			String siguienteReserva = proximaReserva
					.map(reserva -> DATE_FORMATTER.format(reserva.getFecha())
							+ " | "
							+ TIME_FORMATTER.format(reserva.getHoraInicio())
							+ " - "
							+ TIME_FORMATTER.format(reserva.getHoraFin()))
					.orElse("Sin reservas pendientes");

			List<String> equipamientoSala = equipamientos.isEmpty()
					? Collections.singletonList("Sin equipamiento asignado")
					: equipamientos.stream()
							.map(equipamiento -> equipamiento.getNombre() + " (" + equipamiento.getCantidad() + ")")
							.toList();

			return new SalaDisponibilidadDTO(
					sala.getId(),
					sala.getNombre(),
					sala.getTipo(),
					sala.getAforo(),
					sala.getDescripcion(),
					disponibleAhora,
					estadoActual,
					siguienteReserva,
					equipamientoSala);
		}).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<EquipamientoVistaDTO> listarEquipamiento() {
		return equipamientoRepository.findAllByOrderBySalaNombreAscNombreAsc().stream()
				.map(equipamiento -> new EquipamientoVistaDTO(
						equipamiento.getSala().getNombre(),
						equipamiento.getNombre(),
						equipamiento.getCategoria(),
						equipamiento.getCantidad()))
				.toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReservaSalaVistaDTO> listarReservasFuturas() {
		LocalDate hoy = LocalDate.now();
		LocalTime ahora = LocalTime.now();

		return reservaSalaRepository.findAllByOrderByFechaAscHoraInicioAsc().stream()
				.filter(reserva -> reserva.getFecha().isAfter(hoy)
						|| (reserva.getFecha().equals(hoy) && !reserva.getHoraFin().isBefore(ahora)))
				.map(reserva -> new ReservaSalaVistaDTO(
						reserva.getSala().getNombre(),
						reserva.getNombreSolicitante() + " " + reserva.getApellidoSolicitante(),
						DATE_FORMATTER.format(reserva.getFecha()),
						TIME_FORMATTER.format(reserva.getHoraInicio()) + " - "
								+ TIME_FORMATTER.format(reserva.getHoraFin())))
				.toList();
	}

	private Sala obtenerSala(Long salaId) {
		if (salaId == null) {
			throw new IllegalArgumentException("Debes seleccionar una sala.");
		}

		return salaRepository.findById(salaId)
				.orElseThrow(() -> new IllegalArgumentException("La sala seleccionada no existe."));
	}

	private String normalize(String value) {
		return value == null ? "" : value.trim();
	}

	private String normalizeNullable(String value) {
		String normalized = normalize(value);
		return normalized.isEmpty() ? null : normalized;
	}
}
