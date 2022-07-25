package com.smartfit.app.smartfitmanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.smartfit.app.smartfitmanager.Services.ColaboradorServicio;

@Controller
public class LoginController {

	@Autowired
	private ColaboradorServicio servicio;
	
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}
	
	@GetMapping("/")
	public String verPaginaDeInicio(Model modelo) {
		modelo.addAttribute("Colaboradors", servicio.listarColaboradors());
		return "home";
	}

	@GetMapping("/salas")
	public String verPaginaDeSalas(Model modelo) {
		//modelo.addAttribute("Salas", servicio.listarSalas());
		return "salas";
	}

	@GetMapping("/registrarmaterial")
	public String verPaginaDeRegistrarMaterial(Model modelo) {
		//modelo.addAttribute("Salas", servicio.listarSalas());
		return "registrarmaterial";
	}

	@GetMapping("/registrarsalas")
	public String verPaginaDeRegistrarSala(Model modelo) {
		//modelo.addAttribute("Salas", servicio.listarSalas());
		return "registrarsalas";
	}

	@GetMapping("/pedirsala")
	public String verPaginaDePedirSala(Model modelo) {
		//modelo.addAttribute("Salas", servicio.listarSalas());
		return "pedirsala";
	}



}