package com.dsniatecki.yourfleetmanager.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidStringValidator implements ConstraintValidator<ValidString, String> {

    private Integer min;
    private Integer max;
    private String messageEmpty;
    private String messageTooShort;
    private String messageTooLong;


    @Override
    public void initialize(ValidString field) {
        min = field.min();
        max = field.max();
        messageEmpty = field.messageEmpty();
        messageTooShort = field.messageTooShort();
        messageTooLong = field.messageTooLong();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        if (value.isEmpty()) {
            context.buildConstraintViolationWithTemplate(messageEmpty).addConstraintViolation();
            return false;
        }
        else if ((min > 0 ) && (value.length() < min )) {
            context.buildConstraintViolationWithTemplate( messageTooShort).addConstraintViolation();
            return false;
        }
        else if (( max < Integer.MAX_VALUE) && ( value.length() > max)) {
            context.buildConstraintViolationWithTemplate(messageTooLong).addConstraintViolation();
            return false;
        }

        return true;
    }
}
