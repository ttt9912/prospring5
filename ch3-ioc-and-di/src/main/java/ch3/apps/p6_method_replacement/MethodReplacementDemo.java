package ch3.apps.p6_method_replacement;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MethodReplacementDemo {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("methodReplacement_context.xml");
        ctx.refresh();


        System.out.println("Method replacement");
        ReplacementTarget replacementTarget = ctx.getBean("replacementTarget", ReplacementTarget.class);
        String out = replacementTarget.formatMessage("hello");
        System.out.println(out);


        System.out.println("\nNo method replacement. Uses given method.");
        ReplacementTarget standardTarget = (ReplacementTarget) ctx.getBean("standardTarget");
        String out1 = standardTarget.formatMessage("hello");
        System.out.println(out1);
    }
}
