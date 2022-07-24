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
}