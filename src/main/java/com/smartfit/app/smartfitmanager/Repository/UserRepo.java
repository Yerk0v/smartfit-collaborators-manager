package com.smartfit.app.smartfitmanager.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartfit.app.smartfitmanager.Entity.Colaborador;

@Repository

public interface UserRepo extends JpaRepository<Colaborador,String>{

	Optional<Colaborador> findByEmail(String email);

}
