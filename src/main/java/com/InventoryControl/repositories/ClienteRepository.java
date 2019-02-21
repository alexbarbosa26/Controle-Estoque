package com.InventoryControl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.InventoryControl.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente , Integer> {

}
