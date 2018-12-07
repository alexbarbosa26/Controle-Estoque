package com.InventoryControl.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
