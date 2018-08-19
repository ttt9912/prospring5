package ch5.p5_declarative_aop.with_proxyFactoryBean;

class Documentarist {

    private GrammyGuitarist guitarist;

    void execute() {
        guitarist.sing();
        guitarist.talk();
    }

    public GrammyGuitarist getGuitarist() {
        return guitarist;
    }

    public void setGuitarist(final GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }
}
