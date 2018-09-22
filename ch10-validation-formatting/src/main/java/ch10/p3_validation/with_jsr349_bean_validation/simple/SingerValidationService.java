package ch10.p3_validation.with_jsr349_bean_validation.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service("singerValidationService")
class SingerValidationService {

    @Autowired
    private Validator validator; // javax.validation

    // returns validation results
    Set<ConstraintViolation<Singer>> validateSinger(Singer singer) {
        return validator.validate(singer);
    }
}
