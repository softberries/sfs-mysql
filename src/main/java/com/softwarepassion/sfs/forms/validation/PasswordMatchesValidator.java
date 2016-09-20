package com.softwarepassion.sfs.forms.validation;


import org.mvel2.MVEL;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    private static final String PASSWORDS_DONT_MATCH_MESSAGE = "Passwords do not match!";

    private String field;
    private String verifyField;


    public void initialize(PasswordMatches constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.verifyField = constraintAnnotation.verifyField();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldObj = MVEL.getProperty(field, value);
        Object verifyFieldObj = MVEL.getProperty(verifyField, value);

        boolean neitherSet = (fieldObj == null) && (verifyFieldObj == null);

        if (neitherSet) {
            return true;
        }

        boolean matches = (fieldObj != null) && fieldObj.equals(verifyFieldObj);

        if (!matches) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(PASSWORDS_DONT_MATCH_MESSAGE)
                    .addPropertyNode(verifyField)
                    .addConstraintViolation();
        }

        return matches;
    }
}