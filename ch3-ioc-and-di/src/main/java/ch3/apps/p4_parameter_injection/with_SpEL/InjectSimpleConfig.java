package ch3.apps.p4_parameter_injection.with_SpEL;

/*
 * Values werden hier definiert und dann im context
 * mittels SpEL ins 'tatsächliche' Bean übertragen.
 */

class InjectSimpleConfig {

    private String name = "Glenn Quagmire";
    private int age = 40;
    private float height = 1.95f;
    private boolean programmer = false;
    private Long ageInSeconds = 123456789L;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public float getHeight() {
        return height;
    }

    public boolean isProgrammer() {
        return programmer;
    }

    public Long getAgeInSeconds() {
        return ageInSeconds;
    }
}
