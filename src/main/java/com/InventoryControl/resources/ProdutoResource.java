package com.InventoryControl.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.InventoryControl.domain.Produto;
import com.InventoryControl.dto.ProdutoDTO;
import com.InventoryControl.dto.ProdutoUpdateDTO;
import com.InventoryControl.resources.utils.URL;
import com.InventoryControl.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	 @RequestMapping(value="/list-produto",method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> objList = service.findAll();

		return ResponseEntity.ok().body(objList);
	}

	@PreAuthorize("hasAnyRole('ADMIN','MODERADOR')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ProdutoDTO objDTO) {
		Produto obj = service.fromDTO(objDTO);

		obj = service.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCodigo())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> findProdutos(@RequestParam(value = "sites", defaultValue = "") String sites,
			@RequestParam(value = "categorias", defaultValue = "") String codCategoria) {

		List<Integer> ids = URL.decodeIntList(sites);
		List<Integer> codCat = URL.decodeIntList(codCategoria);

		List<Produto> list = service.findByProduto(ids, codCat);

		return ResponseEntity.ok().body(list);

	}
	
	@RequestMapping(value = "/site", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> findAllProdutoBySites(
			@RequestParam(value = "sites", defaultValue = "") String sites) {

		List<Integer> ids = URL.decodeIntList(sites);

		List<Produto> list = service.findAllProdutosBySites(ids);

		return ResponseEntity.ok().body(list);

	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> dashboardProdutos(
			@RequestParam(value = "sites", defaultValue = "") String sites,
			@RequestParam(value = "categorias", defaultValue = "") String codCategoria) {

		List<Integer> ids = URL.decodeIntList(sites);
		List<Integer> codCat = URL.decodeIntList(codCategoria);

		List<Produto> list = service.dashboardProduto(ids, codCat);

		return ResponseEntity.ok().body(list);

	}

	@RequestMapping(value = "/dashboard/categoria", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> dashboardProdutosCategoria(
			@RequestParam(value = "sites", defaultValue = "") String sites,
			@RequestParam(value = "categorias", defaultValue = "") String codCategoria) {

		List<Integer> ids = URL.decodeIntList(sites);
		List<Integer> codCat = URL.decodeIntList(codCategoria);

		List<Produto> list = service.dashboardProdutoCategoria(ids, codCat);

		return ResponseEntity.ok().body(list);

	}
	
	@RequestMapping(value = "/dashboard/list-categoria", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> dashboardProdutosAllCategoria(
			@RequestParam(value = "sites", defaultValue = "") String sites) {

		List<Integer> ids = URL.decodeIntList(sites);

		List<Produto> list = service.dashboardProdutoAllCategoria(ids);

		return ResponseEntity.ok().body(list);

	}
	
	@RequestMapping(value = "/dashboard/sites", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> dashboardProdutosSites(
			@RequestParam(value = "sites", defaultValue = "") String sites) {

		List<Integer> ids = URL.decodeIntList(sites);

		List<Produto> list = service.dashboardProdutoSite(ids);

		return ResponseEntity.ok().body(list);

	}
	
	@PreAuthorize("hasAnyRole('ADMIN','MODERADOR')")
	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ProdutoUpdateDTO objDTO, @PathVariable Integer id) {

		Produto obj = service.fromUpdateDTO(objDTO);
		obj.setCodigo(id);
		obj = service.update(obj);

		return ResponseEntity.noContent().build();

	}
}
