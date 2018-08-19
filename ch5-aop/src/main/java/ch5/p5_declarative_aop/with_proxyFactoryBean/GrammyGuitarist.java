package ch5.p5_declarative_aop.with_proxyFactoryBean;


class GrammyGuitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("Gravitiy is working against me");
    }

    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    void rest() {
        System.out.println("zzz");
    }

    void talk() {
        System.out.println("talk");
    }
}
