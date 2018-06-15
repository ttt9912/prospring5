package ch3.apps.p9_autowiring.with_configuration.simple_beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
class Target {
    private Foo fooOne;
    private Foo fooTwo;
    private Bar bar;

    public Target() {
        System.out.println("NoArgsConstructor called");
    }

    public Target(Foo foo) {
        System.out.println("Target(Foo) called");
    }

    public Target(Foo foo, Bar bar) {
        System.out.println("AllArgsConstructor called");
    }

    @Autowired
    public void setFooOne(Foo fooOne) {
        System.out.println("setFooOne called");
        this.fooOne = fooOne;
    }

    @Autowired
    public void setFooTwo(Foo fooTwo) {
        System.out.println("setFooTwo called");
        this.fooTwo = fooTwo;
    }

    @Autowired
    public void setBar(Bar bar) {
        System.out.println("setBar called");
        this.bar = bar;
    }

    @Override
    public String toString() {
        return "Target{" +
                "fooOne=" + fooOne +
                ", fooTwo=" + fooTwo +
                ", bar=" + bar +
                '}';
    }
}
