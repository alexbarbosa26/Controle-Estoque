package com.InventoryControl.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.InventoryControl.domain.Sites;
import com.InventoryControl.domain.Usuario;
import com.InventoryControl.dto.SitesDTO;
import com.InventoryControl.dto.UsuarioDTO;
import com.InventoryControl.services.SiteService;
import com.InventoryControl.services.UsuarioService;


@RestController
@RequestMapping(value="/sites")
public class SiteResource {
	
	@Autowired
	private SiteService service;
	
	@Autowired
	private UsuarioService usuService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<SitesDTO>> findAll(){
		
		List<Sites> objList=service.findAll();
		
		List<SitesDTO> listDto = objList.stream().map(obj -> new SitesDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Sites obj){
		
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCodigo())
				.toUri();

		return ResponseEntity.created(uri).build();
		
	}
	
	@RequestMapping(value="/{codSite}/usuarios",method=RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> findUsuaurioSite(@PathVariable Integer codSite){
		
		List<Usuario> list = usuService.findUsuarioSite(codSite);
		List<UsuarioDTO> listDto = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDto);
		
	}
}
