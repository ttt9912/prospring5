package ch10.p3_validation.with_spring_validator;

import ch10.Singer;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/*
 * validate(): performs validation
 */
@Component("singerValidator")
class SingerValidator implements Validator {

    @Override
    public boolean supports(final Class<?> aClass) {
        return Singer.class.equals(aClass);
    }

    @Override
    public void validate(final Object o, final Errors errors) {
        // errors: validation result is stored in this Errors instance
        // "firstName.empty": error code, can be used for looking up messages from resource bundles
        ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty");
    }
}
