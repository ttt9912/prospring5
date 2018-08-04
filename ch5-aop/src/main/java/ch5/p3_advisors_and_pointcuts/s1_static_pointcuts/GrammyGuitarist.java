package ch5.p3_advisors_and_pointcuts.s1_static_pointcuts;

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
