package com.smartfit.app.smartfitmanager.Security;

import com.smartfit.app.smartfitmanager.Services.ColaboradorServicio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private final ColaboradorServicio colaboradorServicio;
	private final BCryptPasswordEncoder passwordEncoder;

	public SecurityConfiguration(
			ColaboradorServicio colaboradorServicio,
			BCryptPasswordEncoder passwordEncoder) {
		this.colaboradorServicio = colaboradorServicio;
		this.passwordEncoder = passwordEncoder;
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(colaboradorServicio);
		auth.setPasswordEncoder(passwordEncoder);
		return auth;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authenticationProvider(authenticationProvider())
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(
								"/registro/**",
								"/login",
								"/scripts/**",
								"/css/**",
								"/img/**")
						.permitAll()
						.anyRequest()
						.authenticated())
				.formLogin(form -> form
						.loginPage("/login")
						.permitAll())
				.logout(logout -> logout
						.invalidateHttpSession(true)
						.clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/login?logout")
						.permitAll());

		return http.build();
	}
}
