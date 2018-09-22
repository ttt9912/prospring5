package ch10.p3_validation.with_jsr349_bean_validation.custom;

import ch10.p3_validation.with_jsr349_bean_validation.Genre;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
 * ConstraintValidator<CheckCountrySinger, Singer>: validator checks the @CheckCountrySinger
 * annotation on the Singer classes
 */
class CountrySingerValidator implements ConstraintValidator<CheckCountrySinger, Singer> {

    @Override
    public void initialize(final CheckCountrySinger constraintAnnotation) {
    }

    @Override
    public boolean isValid(final Singer singer, final ConstraintValidatorContext constraintValidatorContext) {
        return singer.getGenre() == null
                || (!singer.getGenre().equals(Genre.COUNTRY)
                || (singer.getLastName() != null && singer.getGender() != null));
    }
}
