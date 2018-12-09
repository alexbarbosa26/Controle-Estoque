package com.InventoryControl.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.InventoryControl.domain.Usuario;
import com.InventoryControl.dto.UsuarioDTO;
import com.InventoryControl.services.UsuarioService;

@RestController
@RequestMapping(value="/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> objList=service.findAll();
		
		return ResponseEntity.ok().body(objList);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDTO objtDTO){

		Usuario obj = service.fromDTO(objtDTO);
		
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCodigo())
				.toUri();

		return ResponseEntity.created(uri).build();
		
	}

}
