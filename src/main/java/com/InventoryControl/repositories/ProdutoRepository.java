package com.InventoryControl.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.InventoryControl.domain.Categoria;
import com.InventoryControl.domain.Produto;
import com.InventoryControl.domain.Sites;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(@Param("nome") String nome,
			@Param("categorias") List<Categoria> categorias, Pageable pageRequest);

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE cat = :categoriaCod AND obj.site IN :site ORDER BY obj.nome")
	public List<Produto> findProdutos(@Param("site") List<Sites> site,
			@Param("categoriaCod") List<Categoria> categoriaCod);

	@Transactional(readOnly = true)
	@Query("SELECT obj.nome, SUM(obj.quantidade) as qtd FROM Produto obj INNER JOIN obj.categorias cat WHERE cat = :categoriaCod AND obj.site IN :site GROUP BY obj.nome ORDER BY obj.quantidade")
	public List<Produto> dashboardProduto(@Param("site") List<Sites> site,
			@Param("categoriaCod") List<Categoria> categoriaCod);

	@Transactional(readOnly = true)
	@Query("SELECT cat.nome, SUM(obj.quantidade) as qtd FROM Produto obj INNER JOIN obj.categorias cat WHERE cat IN :categoriaCod AND obj.site IN :site GROUP BY cat.nome ")
	public List<Produto> dashboardProdutoCategoria(@Param("site") List<Sites> site,
			@Param("categoriaCod") List<Categoria> categoriaCod);
	
	@Transactional(readOnly = true)
	@Query("SELECT cat.nome, SUM(obj.quantidade) as qtd FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.quantidade > 0 AND obj.site IN :site GROUP BY cat.nome ")
	public List<Produto> dashboardProdutoAllCategoria(@Param("site") List<Sites> site);
	
	@Transactional(readOnly = true)
	@Query("SELECT obj FROM Produto obj WHERE obj.quantidade > 0 ORDER BY obj.quantidade DESC")
	public List<Produto> findAllOrderByQuantidade();
	
	@Transactional(readOnly=true)
	@Query("SELECT obj FROM Produto obj WHERE obj.site IN :site ORDER BY obj.quantidade DESC")
	public List<Produto> dashboardProdutoSite(@Param("site") List<Sites> site);
}
