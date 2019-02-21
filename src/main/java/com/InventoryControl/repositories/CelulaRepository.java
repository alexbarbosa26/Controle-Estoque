package com.InventoryControl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.InventoryControl.domain.Celula;

@Repository
public interface CelulaRepository extends JpaRepository<Celula, Integer> {

}
