package com.InventoryControl.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.InventoryControl.domain.Sites;
import com.InventoryControl.services.SiteService;


@RestController
@RequestMapping(value="/sites")
public class SiteResource {
	
	@Autowired
	private SiteService service;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Sites>> findAll(){
		
		List<Sites> objList=service.findAll();
		
		return ResponseEntity.ok().body(objList);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Sites obj){
		
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCodigo())
				.toUri();

		return ResponseEntity.created(uri).build();
		
	}
}
