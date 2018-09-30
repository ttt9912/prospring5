package ch10.p3_validation.with_jsr349_bean_validation.custom;

import ch10.p3_validation.with_jsr349_bean_validation.Genre;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.validation.ConstraintViolation;
import java.util.Set;

/*
 * allows convenient class level validation instead of property level annotation
 *
 * create a custom validator and a custom annotation for it
 */
class Demo {

    @Test
    void jsr349_bean_validation() {
        // using jsr-349 bean validation
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(AppConfig.class);

        SingerValidationService singerValidationService =
                ctx.getBean(SingerValidationService.class);


        System.out.println("\n--- Country Singer ---");
        final Singer singer = new Singer();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setGenre(Genre.COUNTRY);
        singer.setGender(null);

        final Set<ConstraintViolation<Singer>> constraintViolations =
                singerValidationService.validateSinger(singer);

        printViolations(constraintViolations);

        System.out.println("\n--- Non-Country Singer ---");
        final Singer singer2 = new Singer();
        singer2.setFirstName("John");
        singer2.setLastName("Mayer");
        singer.setGenre(Genre.POP);
        singer2.setGenre(null);
        singer2.setGender(null);

        final Set<ConstraintViolation<Singer>> constraintViolations2 =
                singerValidationService.validateSinger(singer2);

        printViolations(constraintViolations2);

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
