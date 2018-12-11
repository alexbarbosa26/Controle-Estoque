package com.InventoryControl.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.InventoryControl.domain.Trocas;
import com.InventoryControl.domain.Usuario;

@Repository
public interface TrocasRepository extends JpaRepository<Trocas, Integer>{
	
	@Transactional(readOnly=true)
	Page<Trocas> findByUsuario(Usuario usuario, Pageable pageRequest);

}
