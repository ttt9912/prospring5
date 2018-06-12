package ch3.apps.p4_parameter_injection.with_SpEL;

import org.springframework.context.support.GenericXmlApplicationContext;

class InjectSimpleSpEL {

    private String name;
    private int age;
    private float height;
    private boolean programmer;
    // Alternativ: annotation style anstatt in xml:
    // @Value("#{injectSimpleConfig.ageInSeconds}")
    private Long ageInSeconds;

    public static void main(String... args){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("parameterInjection_SpEL_context.xml");
        ctx.refresh();

        InjectSimpleSpEL simple = (InjectSimpleSpEL) ctx.getBean("injectSimpleSpel");
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
        return "InjectSimpleSpEL{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", programmer=" + programmer +
                ", ageInSeconds=" + ageInSeconds +
                '}';
    }
}
