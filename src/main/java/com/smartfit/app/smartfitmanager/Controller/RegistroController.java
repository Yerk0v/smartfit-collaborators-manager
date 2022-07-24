package com.smartfit.app.smartfitmanager.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartfit.app.smartfitmanager.Controller.dto.ColaboradorRegistroDTO;
import com.smartfit.app.smartfitmanager.Services.ColaboradorServicio;

@Controller
@RequestMapping("/registro")
public class RegistroController {

	private ColaboradorServicio ColaboradorServicio;

	public RegistroController(ColaboradorServicio ColaboradorServicio) {
		super();
		this.ColaboradorServicio = ColaboradorServicio;
	}
	
	@ModelAttribute("Colaborador")
	public ColaboradorRegistroDTO retornarNuevoColaboradorRegistroDTO() {
		return new ColaboradorRegistroDTO();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}
	
	@PostMapping
	public String registrarCuentaDeColaborador(@ModelAttribute("Colaborador") ColaboradorRegistroDTO registroDTO) {
		ColaboradorServicio.guardar(registroDTO);
		return "redirect:/registro?exito";
	}
}
