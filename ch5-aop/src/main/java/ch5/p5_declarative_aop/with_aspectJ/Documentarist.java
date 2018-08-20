package ch5.p5_declarative_aop.with_aspectJ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("documentarist")
class Documentarist {

    protected GrammyGuitarist guitarist;

    public void execute() {
        guitarist.sing();

        Guitar guitar = new Guitar();
        guitar.setBrand("Gibson");

        guitarist.sing(guitar);
        guitarist.talk();
    }

    @Autowired
    @Qualifier("johnMayer")
    public void setGuitarist(final GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }
}
