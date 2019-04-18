package com.InventoryControl.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.InventoryControl.domain.Cliente;
import com.InventoryControl.dto.ClienteDTO;
import com.InventoryControl.repositories.ClienteRepository;
import com.InventoryControl.resources.exception.FieldMessage;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteDTO> {

	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize(ClienteInsert constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		Cliente nomeCliente = repo.findByNome(objDto.getNome());
		if (nomeCliente != null) {
			list.add(new FieldMessage("nome", "Cliente já existente"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
