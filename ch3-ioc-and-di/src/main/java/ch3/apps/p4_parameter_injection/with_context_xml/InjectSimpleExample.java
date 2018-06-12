package ch3.apps.p4_parameter_injection.with_context_xml;

import org.springframework.context.support.GenericXmlApplicationContext;

/*
 * Very simple standalone example of field injecction.
 *
 * Values are injected in the context xml file.
 */

class InjectSimpleExample {

    private String name;
    private int age;
    private float height;
    private boolean programmer;
    private Long ageInSeconds;


    public static void main(String... args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("parameterInjection_context.xml");
        ctx.refresh();

        InjectSimpleExample simple = (InjectSimpleExample) ctx.getBean("injectSimpleExample");
        System.out.println(simple);
        ctx.close();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isProgrammer() {
        return programmer;
    }

    public void setProgrammer(boolean programmer) {
        this.programmer = programmer;
    }

    public Long getAgeInSeconds() {
        return ageInSeconds;
    }

    public void setAgeInSeconds(Long ageInSeconds) {
        this.ageInSeconds = ageInSeconds;
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
