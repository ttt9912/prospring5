package ch4.p1_bean_lifecycles.with_context_xml;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/*
 * InitializingBean, afterPropertiesSet(): initialization method
 *
 */
class SingerWithInterface implements InitializingBean, DisposableBean {
    private static final String DEFAULT_NAME = "Eric Clapton";

    private String name;
    private int age = Integer.MIN_VALUE;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing Bean");

        if (name == null) {
            System.out.println("using default name");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE) {
            System.out.println("you must set the age property of any beans of type " + Singer.class);
            // throw exception
        }
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroying bean");
        // cleanup
    }

    @Override
    public String toString() {
        return "SingerWithInterface{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
