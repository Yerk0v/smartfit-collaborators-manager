package com.smartfit.app.smartfitmanager.Services;

import java.util.List;

import com.smartfit.app.smartfitmanager.Controller.dto.EquipamientoRegistroDTO;
import com.smartfit.app.smartfitmanager.Controller.dto.EquipamientoVistaDTO;
import com.smartfit.app.smartfitmanager.Controller.dto.ReservaSalaDTO;
import com.smartfit.app.smartfitmanager.Controller.dto.ReservaSalaVistaDTO;
import com.smartfit.app.smartfitmanager.Controller.dto.SalaDisponibilidadDTO;
import com.smartfit.app.smartfitmanager.Controller.dto.SalaRegistroDTO;
import com.smartfit.app.smartfitmanager.Entity.Sala;

public interface SalaService {

	void registrarSala(SalaRegistroDTO salaRegistroDTO);

	void registrarEquipamiento(EquipamientoRegistroDTO equipamientoRegistroDTO);

	void reservarSala(ReservaSalaDTO reservaSalaDTO);

	List<Sala> listarSalas();

	List<SalaDisponibilidadDTO> listarDisponibilidadDeSalas();

	List<EquipamientoVistaDTO> listarEquipamiento();

	List<ReservaSalaVistaDTO> listarReservasFuturas();
}
