package com.InventoryControl.services;

import java.util.Arrays;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.InventoryControl.domain.Sites;
import com.InventoryControl.domain.Usuario;
import com.InventoryControl.dto.UsuarioDTO;
import com.InventoryControl.exceptions.DataIntegrityException;
import com.InventoryControl.repositories.SiteRepository;
import com.InventoryControl.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private SiteRepository repoSite;

	public Usuario insert(Usuario obj) {

		obj.setCodigo(null);
		return repo.save(obj);

	}
	
	public Usuario fromDTO(UsuarioDTO dto) {
		
		Usuario usuario = new Usuario(null, dto.getMatricula(), dto.getNome(), dto.getEmail(), dto.getSenha());
		Sites site = repoSite.findOne(dto.getCodSite());
		
		usuario.getSite().addAll(Arrays.asList(site));
		site.getUsuarios().addAll(Arrays.asList(usuario));
		
		return usuario;
		
	}

	public Usuario buscarId(Integer cod) {

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
