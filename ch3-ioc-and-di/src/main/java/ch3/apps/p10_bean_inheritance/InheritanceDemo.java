package ch3.apps.p10_bean_inheritance;


/*
 * Bean Inheritance:
 *  #1: multiple beans that are the same type
 *  #2: multiple beans that implement a shared interface
 *
 *
 */

import org.springframework.context.support.GenericXmlApplicationContext;

class InheritanceDemo {

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("beanInheritance_context.xml");
        ctx.refresh();

        Singer parent = (Singer) ctx.getBean("parent");
        Singer child = (Singer) ctx.getBean("child");

        System.out.println("Parent Singer: " + parent);
        System.out.println("Child Singer: " + child);
    }
}
