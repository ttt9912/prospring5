package ch10.p3_validation.with_jsr349_bean_validation.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @Constraint: it's a validator!
 * validatedBy: locates validation logic
 *
 * @Target(ElementType.TYPE): annotation will be applicable on class level only
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = CountrySingerValidator.class)
@interface CheckCountrySinger {

    String message() default "Country Singer should have gender and last name defined";

    Class<?>[] groups() default {};

    // allows to attach additional information to the constraint
    Class<? extends Payload>[] payload() default {};
}
