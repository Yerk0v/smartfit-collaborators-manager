package com.smartfit.app.smartfitmanager.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartfit.app.smartfitmanager.Entity.Sala;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {

	boolean existsByNombreIgnoreCase(String nombre);

	List<Sala> findAllByOrderByNombreAsc();
}
