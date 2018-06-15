package ch3.apps.p9_autowiring.with_configuration.related_beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
class Target {
    private Foo fooOne;
    private Foo fooTwo;

    public Target() {
        System.out.println("NoArgsConstructor called");
    }

    public Target(Foo fooOne, Foo fooTwo) {
        System.out.println("AllArgsConstructor called");
        this.fooOne = fooOne;
        this.fooTwo = fooTwo;
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

    @Override
    public String toString() {
        return "Target{" +
                "fooOne=" + fooOne +
                ", fooTwo=" + fooTwo +
                '}';
    }
}
