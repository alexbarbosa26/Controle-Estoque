package com.InventoryControl.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.InventoryControl.domain.ItemTroca;
import com.InventoryControl.domain.Produto;
import com.InventoryControl.domain.Trocas;
import com.InventoryControl.domain.Usuario;
import com.InventoryControl.exceptions.AuthorizationException;
import com.InventoryControl.repositories.ItemTrocaRepository;
import com.InventoryControl.repositories.ProdutoRepository;
import com.InventoryControl.repositories.TrocasRepository;
import com.InventoryControl.repositories.UsuarioRepository;
import com.InventoryControl.resources.exception.ObjectNotFoundException;
import com.InventoryControl.security.UserSS;


@Service
public class TrocasService {
	
	@Autowired
	private TrocasRepository repo;
	
	@Autowired
	private UsuarioRepository repoUsuario;
	
	@Autowired
	private ProdutoRepository repoProduto;
	
	@Autowired
	private ItemTrocaRepository repoItem;
	
	@Autowired
	private EmailService emailService;
	
	//metodo para registrar uma baixa no estoque
	@Transactional
	public Trocas insert(Trocas obj){
		
		obj.setCodigo(null);
		obj.setDataTroca(new Date());
		obj.setUsuario(repoUsuario.findOne(obj.getUsuario().getCodigo()));
		
		obj = repo.save(obj);
		
		//adiciona a quantidade e todo os itens que foram selecionados para dar baixa no estoque
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
		
		//metodo para enviar por email as informações 
		emailService.sendOrderConfirmationHtmlEmail(obj);
		
		return obj;
		
	}
	
	
	//metodo para buscar e litar todas as trocas
	public List<Trocas> findAll(){
		return repo.findAll();
	}
	
	public List<Trocas> dashboardTrocas(String clienteId, Integer siteId, Integer periodo) {
		return repo.dashboardTrocas(clienteId, siteId, periodo);
	}
	
	
	//metodos que lista todos as trocas de forma pagina na qual o usuario deva estar logado para lista-las
	public Page<Trocas> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		UserSS user = UserService.authenticated();
		
		if(user==null) {
			throw new AuthorizationException("Acesso Negado");
		}
		
		PageRequest pageRequest = new PageRequest(page, linesPerPage,Direction.valueOf(direction),orderBy);
		
		Usuario ususario = repoUsuario.findOne(user.getId());
		
		return repo.findByUsuario(ususario, pageRequest);
		
	}
	

}
