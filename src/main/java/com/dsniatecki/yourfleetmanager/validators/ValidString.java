package com.dsniatecki.yourfleetmanager.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { ValidStringValidator.class })
public @interface ValidString {

    String message() default "Wrong data of String field";
    String messageEmpty() default "is required";
    String messageTooShort() default "Must contain at least {min} characters";
    String messageTooLong() default "Can not contain more than {max} characters";
    int min() default 0;
    int max() default Integer.MAX_VALUE;
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };


}