package ch10.p3_validation.with_jsr349_bean_validation.simple;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.validation.ConstraintViolation;
import java.util.Set;

/*
 * the recommended way!
 *
 * - fully annotation based (@NotNull, etc.)
 * - allows simple custom valitator annotations
 */
class Demo {

    @Test
    void jsr349_bean_validation() {
        // using jsr-349 bean validation
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        SingerValidationService singerValidationService =
                ctx.getBean(SingerValidationService.class);

        final Singer singer = new Singer();
        singer.setFirstName("J");
        singer.setLastName("Mayer");
        singer.setGenre(null);
        singer.setGender(null);

        final Set<ConstraintViolation<Singer>> constraintViolations =
                singerValidationService.validateSinger(singer);

        printViolations(constraintViolations);

        ctx.close();
    }


    private void printViolations(final Set<ConstraintViolation<Singer>> constraintViolations) {
        constraintViolations.forEach(v -> {
            System.out.println("Validation error for property: " + v.getPropertyPath());
            System.out.println("\tvalue: " + v.getInvalidValue());
            System.out.println("\terror message: " + v.getMessage());
        });
    }
}
