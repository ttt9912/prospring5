package ch4.p1_bean_lifecycles.with_context_xml;

/*
 * init(): initialization method that is specified in init-method attribute in context.xml.
 */
class Singer {
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


    public void init() {
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

    public void destroy() {
        System.out.println("destroying bean");
        // cleanup
    }

    @Override
    public String toString() {
        return "Singer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
