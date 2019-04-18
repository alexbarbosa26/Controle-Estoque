package com.InventoryControl.services.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.InventoryControl.dto.ClienteUpdateDTO;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteUpdateDTO> {

	
	@Override
	public void initialize(ClienteUpdate constraintAnnotation) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(ClienteUpdateDTO value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return false;
	}

}
