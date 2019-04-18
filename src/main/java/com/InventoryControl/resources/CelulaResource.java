package com.InventoryControl.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.InventoryControl.domain.Celula;
import com.InventoryControl.dto.CelulaDTO;
import com.InventoryControl.services.CelulaService;

@RestController
@RequestMapping(value="/celula")
public class CelulaResource {
	
	@Autowired
	private CelulaService service;
	
	@RequestMapping(value="/list-celulas",method = RequestMethod.GET)
	public ResponseEntity<List<Celula>> findAll() {
		List<Celula> objList = service.findAll();

		return ResponseEntity.ok().body(objList);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CelulaDTO objDTO) {
		
		Celula obj = service.fromDTO(objDTO);

		obj = service.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCodigo())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
	

}
