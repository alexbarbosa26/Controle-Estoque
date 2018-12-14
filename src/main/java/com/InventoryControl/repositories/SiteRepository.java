package com.InventoryControl.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.InventoryControl.domain.Sites;

@Repository
public interface SiteRepository extends JpaRepository<Sites, Integer>{

	@Transactional(readOnly=true)
	Sites findAllByOrderByNome(String nome);
}
