package com.shyftlab.assesment.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface ValidAge {

	String message() default "Student must be at least {minAge} years old";

	int minAge() default 10;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}