package com.InventoryControl.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.InventoryControl.domain.Usuario;
import com.InventoryControl.dto.UsuarioDTO;
import com.InventoryControl.repositories.UsuarioRepository;
import com.InventoryControl.resources.exception.FieldMessage;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioDTO> {

	@Autowired
	private UsuarioRepository repo;

	@Override
	public void initialize(UsuarioInsert ann) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(UsuarioDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		/*
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}

		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}*/

		Usuario matricula = repo.findByMatricula(objDto.getMatricula());
		if(matricula != null) {
			list.add(new FieldMessage("matricula","Matricula já existente"));
		}
		
		Usuario aux = repo.findByEmail(objDto.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}

}
