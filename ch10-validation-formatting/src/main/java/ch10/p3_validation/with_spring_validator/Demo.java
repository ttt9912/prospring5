package ch10.p3_validation.with_spring_validator;

import ch10.Singer;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;

/*
 * Implement Validator interface (spring) and use ValidationUtils for validation
 * Provide BeanPropertyBindingResult to Validator to store the result in
 */
class Demo {

    @Test
    void spring_validator() {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Validator singerValidator = ctx.getBean("singerValidator", Validator.class);

        final Singer singer = new Singer();
        singer.setFirstName(null);
        singer.setLastName("Mayer");

        // stores the validation result
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(singer, "John");

        ValidationUtils.invokeValidator(singerValidator, singer, result);

        List<ObjectError> errors = result.getAllErrors();
        System.out.println("validation errors for singer:");
        errors.forEach(e -> System.out.println(e.getCode()));

        ctx.close();
    }

}
