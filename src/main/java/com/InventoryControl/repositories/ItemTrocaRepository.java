package com.InventoryControl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.InventoryControl.domain.ItemTroca;

@Repository
public interface ItemTrocaRepository extends JpaRepository<ItemTroca, Integer>{

}
