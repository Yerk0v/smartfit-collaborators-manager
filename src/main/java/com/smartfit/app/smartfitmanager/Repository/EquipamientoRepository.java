package com.smartfit.app.smartfitmanager.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartfit.app.smartfitmanager.Entity.Equipamiento;

@Repository
public interface EquipamientoRepository extends JpaRepository<Equipamiento, Long> {

	List<Equipamiento> findAllByOrderBySalaNombreAscNombreAsc();

	List<Equipamiento> findBySalaIdOrderByNombreAsc(Long salaId);
}
