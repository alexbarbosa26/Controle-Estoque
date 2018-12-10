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

	public Usuario insert(Usuario obj) {

		obj.setCodigo(null);
		return repo.save(obj);

	}
	
	public Usuario fromDTO(UsuarioDTO dto) {
		
		Usuario usuario = new Usuario(null, dto.getMatricula(), dto.getNome(), dto.getEmail(), psw.encode(dto.getSenha()));
		Sites site = repoSite.findOne(dto.getCodSite());
		
		usuario.getSite().addAll(Arrays.asList(site));
		site.getUsuarios().addAll(Arrays.asList(usuario));
		
		return usuario;
		
	}

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

	public List<Usuario> findAll() {

		return repo.findAll();
	}

	public void delete(Integer cod) {
		buscarId(cod);
		try {
			repo.delete(cod);
		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não é possivel excluir o usúario");
		}
	}

}
