package com.InventoryControl.services.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UsuarioUpdateValidator.class)
public @interface UsuarioUpdate {

	String message() default "Erro de validação";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
