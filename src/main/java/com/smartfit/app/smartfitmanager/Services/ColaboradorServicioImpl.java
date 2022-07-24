package com.smartfit.app.smartfitmanager.Services;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.smartfit.app.smartfitmanager.Controller.dto.ColaboradorRegistroDTO;
import com.smartfit.app.smartfitmanager.Entity.Colaborador;
import com.smartfit.app.smartfitmanager.Entity.Rol;
import com.smartfit.app.smartfitmanager.Repository.UserRepo;

@Service
public class ColaboradorServicioImpl implements ColaboradorServicio {

	
	private UserRepo ColaboradorRepositorio;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public ColaboradorServicioImpl(UserRepo ColaboradorRepositorio) {
		super();
		this.ColaboradorRepositorio = ColaboradorRepositorio;
	}

	@Override
	public Colaborador guardar(ColaboradorRegistroDTO registroDTO) {
		Colaborador Colaborador = new Colaborador(registroDTO.getNombre(), 
				registroDTO.getApellido(),registroDTO.getEmail(),
				passwordEncoder.encode(registroDTO.getPassword()),Arrays.asList(new Rol("ROLE_USER")));
		return ColaboradorRepositorio.save(Colaborador);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Colaborador Colaborador = ColaboradorRepositorio.findByEmail(username);
		if(Colaborador == null) {
			throw new UsernameNotFoundException("Colaborador o password inválidos");
		}
		return new User(Colaborador.getEmail(),Colaborador.getPassword(), mapearAutoridadesRoles(Colaborador.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
	
	@Override
	public List<Colaborador> listarColaboradors() {
		return ColaboradorRepositorio.findAll();
	}
}