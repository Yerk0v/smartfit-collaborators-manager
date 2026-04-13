package com.smartfit.app.smartfitmanager.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartfit.app.smartfitmanager.Entity.ReservaSala;
import com.smartfit.app.smartfitmanager.Entity.Sala;

@Repository
public interface ReservaSalaRepository extends JpaRepository<ReservaSala, Long> {

	boolean existsBySalaAndFechaAndHoraInicioLessThanAndHoraFinGreaterThan(
			Sala sala,
			LocalDate fecha,
			LocalTime horaFin,
			LocalTime horaInicio);

	List<ReservaSala> findAllByOrderByFechaAscHoraInicioAsc();

	List<ReservaSala> findBySalaOrderByFechaAscHoraInicioAsc(Sala sala);
}
