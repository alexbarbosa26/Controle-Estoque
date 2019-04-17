package com.InventoryControl.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.InventoryControl.domain.Sites;

@Repository
public interface SiteRepository extends JpaRepository<Sites, Integer>{

	@Transactional(readOnly=true)
	public List<Sites> findAllByOrderByNome();
	
	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Sites obj INNER JOIN obj.clientes cli WHERE cli.codigo IN :cliente_cod")
	public List<Sites> findSitesByClientes(@Param("cliente_cod") Integer codigo);
}
