package com.InventoryControl.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.InventoryControl.domain.Trocas;
import com.InventoryControl.domain.Usuario;

@Repository
public interface TrocasRepository extends JpaRepository<Trocas, Integer>{
	
	@Transactional(readOnly=true)
	Page<Trocas> findByUsuario(Usuario usuario, Pageable pageRequest);
	
	@Transactional(readOnly=true)
	@Query("SELECT item.motivo, SUM(item.quantidadeTroca)"
			+ " FROM Trocas obj"
			+ " INNER JOIN obj.itens item"
			+ " INNER JOIN obj.celula cel"
			+ " INNER JOIN item.codigo.produto.site st"
			+ " WHERE"
			+ " cel.cliente.nome LIKE %:clienteId%"
			+ " AND"
			+ " st.codigo=:siteId"
			+ " GROUP BY item.motivo"
			+ " ORDER BY item.quantidadeTroca DESC")
	public List<Trocas> dashboardTrocas(@Param("clienteId") String clienteId, @Param("siteId") Integer siteId);

}
