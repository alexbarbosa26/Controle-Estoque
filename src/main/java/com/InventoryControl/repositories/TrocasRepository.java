package com.InventoryControl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.InventoryControl.domain.Trocas;

@Repository
public interface TrocasRepository extends JpaRepository<Trocas, Integer>{

}
