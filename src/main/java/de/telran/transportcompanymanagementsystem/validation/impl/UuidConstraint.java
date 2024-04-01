package de.telran.transportcompanymanagementsystem.validation.impl;

import de.telran.transportcompanymanagementsystem.validation.UuidChecker;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

public class UuidConstraint implements ConstraintValidator<UuidChecker, String> {

    private static final String UUID = "^([0-9a-fA-F]){8}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){4}-([0-9a-fA-F]){12}$";

    @Override
    public void initialize(UuidChecker constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String uuid, ConstraintValidatorContext constraintValidatorContext) {
        return Optional.ofNullable(uuid)
                .filter(s -> !s.isBlank())
                .map(s -> s.matches(UUID))
                .orElse(false);
    }
}
