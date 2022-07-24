package com.smartfit.app.smartfitmanager.Services;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.smartfit.app.smartfitmanager.Controller.dto.ColaboradorRegistroDTO;
import com.smartfit.app.smartfitmanager.Entity.Colaborador;


public interface ColaboradorServicio extends UserDetailsService{

	public Colaborador guardar(ColaboradorRegistroDTO registroDTO);
	
	public List<Colaborador> listarColaboradors();
	
}