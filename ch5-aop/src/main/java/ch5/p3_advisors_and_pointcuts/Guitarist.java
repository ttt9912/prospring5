package ch5.p3_advisors_and_pointcuts;

class Guitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("just keep me where the light is");
    }

    void sing2() {
        System.out.println("just keep me where the light is");
    }

    void rest() {
        System.out.println("zzz");
    }
}
