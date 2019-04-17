package com.InventoryControl.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.InventoryControl.domain.Cliente;
import com.InventoryControl.dto.ClienteDTO;
import com.InventoryControl.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Cliente>> findAll() {
		
		List<Cliente> objList = service.findAll();
		
		return ResponseEntity.ok().body(objList);
		
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO objDTO) {
		
		Cliente obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCodigo())
				.toUri();

		return ResponseEntity.created(uri).build();
		
		
	}
	
	@RequestMapping(value="{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){
		
		Cliente obj = service.fromDtoUpdate(objDTO, id);
		
		obj.setCodigo(id);
		
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
		
	}
	

}
