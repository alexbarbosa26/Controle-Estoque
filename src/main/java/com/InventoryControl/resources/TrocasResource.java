package com.InventoryControl.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.InventoryControl.domain.Trocas;
import com.InventoryControl.services.TrocasService;

@RestController
@RequestMapping(value="/trocas")
public class TrocasResource {
	
	@Autowired
	private TrocasService service;

	@PreAuthorize("hasAnyRole('ADMIN','MODERADOR')")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Trocas>> findAll(){
		List<Trocas> objList = service.findAll();
				
		return ResponseEntity.ok().body(objList);
	}
	
	@RequestMapping(value="/motivo/{siteId}/{periodo}", method=RequestMethod.GET)
	public ResponseEntity<List<Trocas>> dashboardTrocas(
			@PathVariable Integer siteId,
			@RequestParam(value="cliente", defaultValue="") String clienteId,
			@PathVariable Integer periodo) {
		
		List<Trocas> objList = service.dashboardTrocas(clienteId, siteId, periodo);
		
		return ResponseEntity.ok().body(objList);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN','MODERADOR')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Trocas obj){
		
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCodigo())
				.toUri();

		return ResponseEntity.created(uri).build();	
		
	}
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ResponseEntity<Page<Trocas>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="dataTroca") String orderBy, 
			@RequestParam(value="direction", defaultValue="DESC") String direction) {
		Page<Trocas> list = service.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(list);
	}
}
