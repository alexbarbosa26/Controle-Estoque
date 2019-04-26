package com.InventoryControl.services;

import java.util.Arrays;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.InventoryControl.domain.Celula;
import com.InventoryControl.domain.Cliente;
import com.InventoryControl.dto.CelulaDTO;
import com.InventoryControl.exceptions.DataIntegrityException;
import com.InventoryControl.repositories.CelulaRepository;

@Service
public class CelulaService {

	@Autowired
	private CelulaRepository repoCelula;
	
	@Autowired
	private ClienteService serviceCliente;

	//Metodo para buscar via Codigo
	public Celula buscarId(Integer codigo) {

		Celula obj = repoCelula.findOne(codigo);

		if (obj == null) {

			throw new ObjectNotFoundException("Codigo: " + codigo,
					"não encontrado, favor verificar o código digitado!");
		}

		return obj;

	}
	
	//Metodo para listar todos os registros
	public List<Celula> findAll(){
		
		return repoCelula.findAll();
		
	}
	
	public List<Celula> findByClienteByCelula(Integer clienteId) {
		
		return repoCelula.findByClienteByCelula(clienteId);
	}
	
	
	//Metodo para inserir dados
	public Celula insert(Celula obj) {
		
		obj.setCodigo(null);
		return repoCelula.save(obj);
	}
	
	//Metodo para validar os dados informados pelo usuario
	public Celula fromDTO(CelulaDTO objDTO) {
		
		Cliente cliente = serviceCliente.buscarId(objDTO.getCliente_cod());
		
		Celula celula = new Celula(null, objDTO.getNome(), objDTO.getPep(), cliente);
		
		cliente.getCelulas().addAll(Arrays.asList(celula));
		
		return celula;
	}
	
	//Metodo para atualizar os dados
	public Celula update(Celula obj) {
		buscarId(obj.getCodigo());
		return repoCelula.save(obj);
	}
	
	//metodo para deletar o dado informado
	public void delete(Integer cod) {
		buscarId(cod);
		try {
			repoCelula.delete(cod);
		} catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possivel excluir o produto");
		}
	}
	

}
