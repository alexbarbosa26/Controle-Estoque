package com.InventoryControl.services;

import java.util.Arrays;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.InventoryControl.domain.Categoria;
import com.InventoryControl.domain.Produto;
import com.InventoryControl.domain.Sites;
import com.InventoryControl.dto.ProdutoDTO;
import com.InventoryControl.exceptions.DataIntegrityException;
import com.InventoryControl.repositories.CategoriaRepository;
import com.InventoryControl.repositories.ProdutoRepository;
import com.InventoryControl.repositories.SiteRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;

	@Autowired
	private CategoriaRepository repoCategoria;

	@Autowired
	private SiteRepository repoSite;

	// metodo que insere um novo produto via DTO
	public Produto insert(Produto obj) {

		repoCategoria.save(obj.getCategorias());

		return repo.save(obj);
	}

	// metodo DTO que valida as informações lançadas pelo usuario
	public Produto fromDTO(ProdutoDTO dto) {

		Categoria categoria = repoCategoria.findOne(dto.getCodCategoria());

		Sites site = repoSite.findOne(dto.getCodSite());

		Produto produtos = new Produto(null, dto.getNome(), dto.getQuantidade(),dto.getImagem(), site);

		categoria.getProdutos().addAll(Arrays.asList(produtos));
		site.getProdutos().addAll(Arrays.asList(produtos));
		produtos.getCategorias().addAll(Arrays.asList(categoria));

		return produtos;

	}

	// metodo para busca de produtos por um ou mais Sites e Categoria
	public List<Produto> findByProduto(List<Integer> codSite, List<Integer> codCategoria) {

		List<Sites> sites = repoSite.findAll(codSite);
		List<Categoria> categoria = repoCategoria.findAll(codCategoria);

		return repo.findProdutos(sites, categoria);
	}
	
	//Metodo para buscar produtos pelo site do usuario
	public List<Produto> findAllProdutosBySites(List<Integer> codSites) {
		
		List<Sites> sites = repoSite.findAll(codSites);
		
		return repo.FindAllProdutosBySites(sites);
	}

	// metodo para busca de produtos por um ou mais Sites e Categoria
	public List<Produto> dashboardProduto(List<Integer> codSite, List<Integer> codCategoria) {

		List<Sites> sites = repoSite.findAll(codSite);
		List<Categoria> categoria = repoCategoria.findAll(codCategoria);

		return repo.dashboardProduto(sites, categoria);
	}
	
	//metodo para buscar os produtos pelo site do usuario
	public List<Produto> dashboardProdutoSite(List<Integer> codSite) {
		
		List<Sites> sites = repoSite.findAll(codSite);
		
		return repo.dashboardProdutoSite(sites);
		
	}

	// metodo para busca de produtos por um ou mais Sites e Categoria
	public List<Produto> dashboardProdutoCategoria(List<Integer> codSite, List<Integer> codCategoria) {

		List<Sites> sites = repoSite.findAll(codSite);
		List<Categoria> categoria = repoCategoria.findAll(codCategoria);

		return repo.dashboardProdutoCategoria(sites, categoria);
	}
	
	// metodo para busca de produtos por um ou mais Sites e Categoria
		public List<Produto> dashboardProdutoAllCategoria(List<Integer> codSite) {

			List<Sites> sites = repoSite.findAll(codSite);

			return repo.dashboardProdutoAllCategoria(sites);
		}

	// metodo que faz a busca via codigo do produto
	public Produto buscarId(Integer cod) {

		Produto obj = repo.findOne(cod);
		if (obj == null) {
			throw new ObjectNotFoundException("Codigo: " + cod, "não encontrado, favor verificar o codigo digitado!");
		}
		return obj;
	}

	// metodo que lista todos os produtos
	public List<Produto> findAll() {
		return repo.findAllOrderByQuantidade();
	}

	// metodo que atualiza as informações dos produtos
	public Produto update(Produto obj) {
		buscarId(obj.getCodigo());
		return repo.save(obj);
	}

	// metodo que deteleta os produtos
	public void delete(Integer cod) {
		buscarId(cod);
		try {
			repo.delete(cod);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir o produto");

		}

	}

}
