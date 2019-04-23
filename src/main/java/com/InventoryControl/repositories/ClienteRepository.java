package com.InventoryControl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.InventoryControl.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente , Integer> {
	
	@Transactional(readOnly=true)
	Cliente findByNome(String nome);
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Cliente obj INNER JOIN obj.site st WHERE obj.situacao <> 2 AND st.codigo IN :site ORDER BY obj.nome")
	public List<Cliente> findAllBySitesAndSituacao(
			@Param("site") List<Integer> site);

}
