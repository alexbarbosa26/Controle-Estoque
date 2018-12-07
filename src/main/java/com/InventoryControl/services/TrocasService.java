package com.InventoryControl.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InventoryControl.domain.Trocas;
import com.InventoryControl.repositories.TrocasRepository;

@Service
public class TrocasService {
	
	@Autowired
	public TrocasRepository repo;
	
	public List<Trocas> findAll(){
		return repo.findAll();
	}

}
