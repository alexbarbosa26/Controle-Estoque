package com.InventoryControl.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.InventoryControl.domain.Trocas;
import com.InventoryControl.services.TrocasService;

@RestController
@RequestMapping(value="/trocas")
public class TrocasResource {
	
	@Autowired
	private TrocasService service;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Trocas>> findAll(){
		List<Trocas> objList = service.findAll();
				
		return ResponseEntity.ok().body(objList);
	}
}
