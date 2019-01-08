package com.InventoryControl.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.InventoryControl.domain.Produto;
import com.InventoryControl.dto.ProdutoDTO;
import com.InventoryControl.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> objList = service.findAll();

		return ResponseEntity.ok().body(objList);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ProdutoDTO objDTO) {
		Produto obj = service.fromDTO(objDTO);
		
		obj = service.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCodigo())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{codSite}/{codCategoria}/sites/categorias",method=RequestMethod.GET)
	public ResponseEntity<List<ProdutoDTO>> findProdutos(@PathVariable Integer codSite, @PathVariable Integer codCategoria){
		
		List<Produto> list = service.findBySite(codSite,codCategoria);
		List<ProdutoDTO> listDto = list.stream().map(obj -> new ProdutoDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
		
	}
}
