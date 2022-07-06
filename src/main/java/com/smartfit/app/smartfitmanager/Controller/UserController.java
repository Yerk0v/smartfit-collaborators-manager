package com.smartfit.app.smartfitmanager.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.smartfit.app.smartfitmanager.Entity.Colaborador;
import com.smartfit.app.smartfitmanager.Repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller

public class UserController {

	@Autowired(required = true)
	private UserRepo repo;

	@GetMapping("/")
	public String login(Model model) {
		Colaborador colaborador = new Colaborador();
		model.addAttribute("colaborador", colaborador);
		return "login";
	}

	@PostMapping("/menu")
	public String loginUser(@ModelAttribute("colaborador") Colaborador colaborador) {
		String email = colaborador.getEmail();
		java.util.Optional<Colaborador> userdata = repo.findByEmail(email);
		if (colaborador.getPassword().equals(userdata.get().getPassword())) {
			return "home";
		} else {
			return "error";
		}
	}

}
