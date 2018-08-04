package ch5.p3_advisors_and_pointcuts.s1_static_pointcuts;

class GuitarHero implements Singer {

    @Override
    public void sing() {
        System.out.println("dream of ways to throw it all away");
    }

    @SimpleAdviceRequired
    public void sing(Guitar guitar) {
        System.out.println("play: " + guitar.play());
    }

    void rest() {
        System.out.println("zzz");
    }
}
