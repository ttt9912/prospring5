package ch5.p5_declarative_aop.with_aspectJ;


import org.springframework.stereotype.Component;

@Component("johnMayer")
class GrammyGuitarist implements Singer {

    @Override
    public String sing() {
        String value = "Gravitiy is working against me";
        System.out.println(value);
        return value;
    }

    public String sing(Guitar guitar) {
        String value = "play: " + guitar.play();
        System.out.println(value);
        return value;
    }

    void rest() {
        System.out.println("zzz");
    }

    void talk() {
        System.out.println("talk");
    }
}
