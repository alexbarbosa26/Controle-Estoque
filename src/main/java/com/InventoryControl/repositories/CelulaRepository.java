package com.InventoryControl.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.InventoryControl.domain.Celula;

@Repository
public interface CelulaRepository extends JpaRepository<Celula, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Celula obj WHERE obj.cliente.codigo = :cliente_id ORDER BY obj.nome DESC")
	public List<Celula> findByClienteByCelula(@Param("cliente_id") Integer cliente_id);
	
}
