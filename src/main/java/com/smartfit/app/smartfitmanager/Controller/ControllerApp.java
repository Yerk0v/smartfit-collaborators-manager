package com.smartfit.app.smartfitmanager.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smartfit.app.smartfitmanager.Entity.Colaborador;
import com.smartfit.app.smartfitmanager.Entity.Insumos;
import com.smartfit.app.smartfitmanager.Repository.UserRepo;
import com.smartfit.app.smartfitmanager.Services.InsumoServicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller

public class ControllerApp {

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

	@RequestMapping("/equipamiento")
	public String verEquipamiento(Model modelo){
		List<Insumos> listaInsumos = insumosServicio.listAll();

	}


}
