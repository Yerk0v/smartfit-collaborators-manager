package com.smartfit.app.smartfitmanager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartfit.app.smartfitmanager.Entity.Colaborador;

@Repository

public interface UserRepo extends JpaRepository<Colaborador,String>{

	public Colaborador findByEmail(String email);

}
