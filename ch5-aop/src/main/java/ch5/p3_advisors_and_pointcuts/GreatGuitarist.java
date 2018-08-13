package ch5.p3_advisors_and_pointcuts;

class GreatGuitarist implements Singer {

    @Override
    public void sing() {
        System.out.println("I shoot the sheriff \n " +
                "but I did not shoot the deputy");
    }
}
