package ch5.p2_advices;

class Guitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("[Guitarist] you're gonna live forever.");
    }
}
