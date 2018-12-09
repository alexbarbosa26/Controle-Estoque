package com.InventoryControl.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.InventoryControl.domain.ItemTroca;
import com.InventoryControl.domain.Produto;
import com.InventoryControl.domain.Trocas;
import com.InventoryControl.repositories.ItemTrocaRepository;
import com.InventoryControl.repositories.ProdutoRepository;
import com.InventoryControl.repositories.TrocasRepository;
import com.InventoryControl.repositories.UsuarioRepository;
import com.InventoryControl.resources.exception.ObjectNotFoundException;


@Service
public class TrocasService {
	
	@Autowired
	public TrocasRepository repo;
	
	@Autowired
	public UsuarioRepository repoUsuario;
	
	@Autowired
	public ProdutoRepository repoProduto;
	
	@Autowired
	public ItemTrocaRepository repoItem;
	
	
	public Trocas insert(Trocas obj){
		
		obj.setCodigo(null);
		obj.setDataTroca(new Date());
		obj.setUsuario(repoUsuario.findOne(obj.getUsuario().getCodigo()));
		
		obj = repo.save(obj);
		
		for(ItemTroca it : obj.getItens()) {
			
			it.setProduto(repoProduto.findOne(it.getProduto().getCodigo()));
			it.setTroca(obj);
			
			if(it.getProduto().getQuantidade() != 0 && it.getProduto().getQuantidade() >= it.getQuantidadeTroca()) {					
					
					Integer valor;
					valor = it.getProduto().getQuantidade() - it.getQuantidadeTroca();
					
					Produto pro = repoProduto.findOne(it.getProduto().getCodigo());
					
					pro.setQuantidade(valor);
					repoProduto.save(Arrays.asList(pro));
				
			}else {
				
				throw new ObjectNotFoundException("Estoque Vazio");
				
			}
			
		}
		
		repoItem.save(obj.getItens());
				
		
		return obj;
		
	}
	
	public List<Trocas> findAll(){
		return repo.findAll();
	}

}
