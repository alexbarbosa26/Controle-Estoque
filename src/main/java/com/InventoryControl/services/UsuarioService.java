package com.InventoryControl.services;

import java.util.Arrays;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.InventoryControl.domain.Sites;
import com.InventoryControl.domain.Usuario;
import com.InventoryControl.dto.UsuarioDTO;
import com.InventoryControl.enums.Perfil;
import com.InventoryControl.exceptions.AuthorizationException;
import com.InventoryControl.exceptions.DataIntegrityException;
import com.InventoryControl.repositories.SiteRepository;
import com.InventoryControl.repositories.UsuarioRepository;
import com.InventoryControl.security.UserSS;

@Service
public class UsuarioService {

	@Autowired
	private BCryptPasswordEncoder psw;
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private SiteRepository repoSite;
	

	//metodo que insere um novo usuario validado por um DTO
	public Usuario insert(Usuario obj) {

		obj.setCodigo(null);
		return repo.save(obj);

	}
	
	//metodo que faz a busca pelo email do usuario caso o usuario esteja logado e tenha a devida permissao
	public Usuario findByEmail(String email) {
		
		UserSS user = UserService.authenticated();
		if(user==null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
			throw new AuthorizationException("Acesso Negado");
			
		}
		
		Usuario obj = repo.findByEmail(email);
		
		if(obj == null) {
			throw new ObjectNotFoundException("Email: " + user.getUsername(), "não encontrado, favor verificar o codigo digitado!");
		}
		
		return obj;
		
	}
	
	//metodo DTO que valida os dados lançados pelo usuario
	public Usuario fromDTO(UsuarioDTO dto) {
		
		Usuario usuario = new Usuario(null, dto.getMatricula(), dto.getNome(), dto.getEmail(), psw.encode(dto.getSenha()));
		Sites site = repoSite.findOne(dto.getCodSite());
		
		usuario.getSite().addAll(Arrays.asList(site));
		site.getUsuarios().addAll(Arrays.asList(usuario));
		
		return usuario;
		
	}

	//metodo que efetua a busca pelo codigo do usuario caso o usuario esteja logado e tenha a devida permissao
	public Usuario buscarId(Integer cod) {
		
		UserSS user = UserService.authenticated();
		
		if(user==null || !user.hasRole(Perfil.ADMIN) && !cod.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
			
		}

		Usuario obj = repo.findOne(cod);

		if (obj == null) {
			throw new ObjectNotFoundException("Codigo: " + cod, "não encontrado, favor verificar o codigo digitado!");
		}
		return obj;
	}
	
	//metodo que lista todos os usuarios
	public List<Usuario> findAll() {

		return repo.findAll();
	}

	//metodo para deletar um usuario pelo codigo
	public void delete(Integer cod) {
		buscarId(cod);
		try {
			repo.delete(cod);
		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não é possivel excluir o usúario");
		}
	}
	
	//metodo para listar os usuario baseado no codigo do site
	public List<Usuario> findUsuarioSite(Integer codigo){
		
		UserSS user = UserService.authenticated();
		
		if(user==null || !user.hasRole(Perfil.ADMIN) && !codigo.equals(user.getId())) {
			throw new AuthorizationException("Acesso Negado");
			
		}
		
		List<Usuario> objList = repo.findUsuariosSites(codigo);		
		
		if(objList == null) {
			throw new ObjectNotFoundException("Não existe site de codigo: " +codigo,"");
		}
		return objList;
		
	}

}
