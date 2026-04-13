package com.smartfit.app.smartfitmanager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smartfit.app.smartfitmanager.Controller.dto.EquipamientoRegistroDTO;
import com.smartfit.app.smartfitmanager.Controller.dto.ReservaSalaDTO;
import com.smartfit.app.smartfitmanager.Controller.dto.SalaRegistroDTO;
import com.smartfit.app.smartfitmanager.Services.SalaService;

@Controller
public class SalaController {

	private final SalaService salaService;

	public SalaController(SalaService salaService) {
		this.salaService = salaService;
	}

	@GetMapping("/salas")
	public String verSalas(Model model) {
		model.addAttribute("salas", salaService.listarDisponibilidadDeSalas());
		return "salas";
	}

	@GetMapping("/registrarsalas")
	public String verFormularioDeSalas(Model model) {
		if (!model.containsAttribute("salaForm")) {
			model.addAttribute("salaForm", new SalaRegistroDTO());
		}
		model.addAttribute("salas", salaService.listarDisponibilidadDeSalas());
		return "registrarsalas";
	}

	@PostMapping("/registrarsalas")
	public String registrarSala(
			@ModelAttribute("salaForm") SalaRegistroDTO salaForm,
			RedirectAttributes redirectAttributes) {
		try {
			salaService.registrarSala(salaForm);
			redirectAttributes.addFlashAttribute("exito", "Sala registrada correctamente.");
		} catch (IllegalArgumentException exception) {
			redirectAttributes.addFlashAttribute("error", exception.getMessage());
			redirectAttributes.addFlashAttribute("salaForm", salaForm);
		}
		return "redirect:/registrarsalas";
	}

	@GetMapping("/registrarmaterial")
	public String verFormularioDeEquipamiento(Model model) {
		if (!model.containsAttribute("equipamientoForm")) {
			model.addAttribute("equipamientoForm", new EquipamientoRegistroDTO());
		}
		model.addAttribute("salas", salaService.listarSalas());
		model.addAttribute("equipamientos", salaService.listarEquipamiento());
		return "registrarmaterial";
	}

	@PostMapping("/registrarmaterial")
	public String registrarEquipamiento(
			@ModelAttribute("equipamientoForm") EquipamientoRegistroDTO equipamientoForm,
			RedirectAttributes redirectAttributes) {
		try {
			salaService.registrarEquipamiento(equipamientoForm);
			redirectAttributes.addFlashAttribute("exito", "Equipamiento registrado correctamente.");
		} catch (IllegalArgumentException exception) {
			redirectAttributes.addFlashAttribute("error", exception.getMessage());
			redirectAttributes.addFlashAttribute("equipamientoForm", equipamientoForm);
		}
		return "redirect:/registrarmaterial";
	}

	@GetMapping("/pedirsala")
	public String verFormularioDeReserva(Model model) {
		if (!model.containsAttribute("reservaForm")) {
			model.addAttribute("reservaForm", new ReservaSalaDTO());
		}
		model.addAttribute("salas", salaService.listarSalas());
		model.addAttribute("reservas", salaService.listarReservasFuturas());
		return "pedirsala";
	}

	@PostMapping("/pedirsala")
	public String reservarSala(
			@ModelAttribute("reservaForm") ReservaSalaDTO reservaForm,
			RedirectAttributes redirectAttributes) {
		try {
			salaService.reservarSala(reservaForm);
			redirectAttributes.addFlashAttribute("exito", "Reserva creada correctamente.");
		} catch (IllegalArgumentException exception) {
			redirectAttributes.addFlashAttribute("error", exception.getMessage());
			redirectAttributes.addFlashAttribute("reservaForm", reservaForm);
		}
		return "redirect:/pedirsala";
	}
}
