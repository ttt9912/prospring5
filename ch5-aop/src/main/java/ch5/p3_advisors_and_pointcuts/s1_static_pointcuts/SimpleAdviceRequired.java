package ch5.p3_advisors_and_pointcuts.s1_static_pointcuts;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
        // applicable on methods and classes
@interface SimpleAdviceRequired {
}
