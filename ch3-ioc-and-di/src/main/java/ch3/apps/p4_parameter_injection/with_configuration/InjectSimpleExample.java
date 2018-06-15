package ch3.apps.p4_parameter_injection.with_configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

/*
 * Very simple standalone example of field injecction.
 */

@Service("injectSimple")
class InjectSimpleExample {

    @Value("Peter Griffin")
    private String name;
    @Value("33")
    private int age;
    @Value("1.88")
    private float height;
    @Value("true")
    private boolean programmer;
    @Value("123456789")
    private Long ageInSeconds;


    public static void main(String... args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("parameterInjection_wAnnotations_context.xml");
        ctx.refresh();

        InjectSimpleExample simple = ctx.getBean(InjectSimpleExample.class);
        System.out.println(simple);
        ctx.close();
    }

    @Override
    public String toString() {
        return "InjectSimpleExample{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", programmer=" + programmer +
                ", ageInSeconds=" + ageInSeconds +
                '}';
    }
}
