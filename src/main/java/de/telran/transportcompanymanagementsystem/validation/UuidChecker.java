package de.telran.transportcompanymanagementsystem.validation;

import jakarta.validation.Constraint;
import de.telran.transportcompanymanagementsystem.validation.impl.UuidConstraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UuidConstraint.class})
public @interface UuidChecker {

    String message() default "** It is not UUID format **";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
