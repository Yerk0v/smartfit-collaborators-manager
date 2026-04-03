package com.smartfit.app.smartfitmanager.Bootstrap;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.smartfit.app.smartfitmanager.Entity.Colaborador;
import com.smartfit.app.smartfitmanager.Entity.Rol;
import com.smartfit.app.smartfitmanager.Repository.UserRepo;

@Component
@ConditionalOnProperty(name = "app.bootstrap-admin.enabled", havingValue = "true")
public class DevelopmentAdminBootstrap implements ApplicationRunner {

	private final UserRepo userRepo;
	private final BCryptPasswordEncoder passwordEncoder;
	private final String datasourceUrl;
	private final String nombre;
	private final String apellido;
	private final String email;
	private final String password;

	public DevelopmentAdminBootstrap(
			UserRepo userRepo,
			BCryptPasswordEncoder passwordEncoder,
			@Value("${spring.datasource.url:}") String datasourceUrl,
			@Value("${app.bootstrap-admin.nombre}") String nombre,
			@Value("${app.bootstrap-admin.apellido}") String apellido,
			@Value("${app.bootstrap-admin.email}") String email,
			@Value("${app.bootstrap-admin.password}") String password) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
		this.datasourceUrl = datasourceUrl;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
	}

	@Override
	public void run(ApplicationArguments args) {
		if (!isLocalDatabase() || userRepo.count() > 0 || userRepo.findByEmail(email) != null) {
			return;
		}

		Colaborador admin = new Colaborador(
				nombre,
				apellido,
				email,
				passwordEncoder.encode(password),
				Collections.singletonList(new Rol("ROLE_ADMIN")));

		userRepo.save(admin);
		System.out.println("Bootstrap admin created for local database: " + email);
	}

	private boolean isLocalDatabase() {
		return datasourceUrl.contains("localhost") || datasourceUrl.contains("127.0.0.1");
	}
}
