package ch5.p5_declarative_aop.with_aopNamespace;

class Documentarist {

    protected GrammyGuitarist guitarist;

    public void execute() {
        guitarist.sing();
        guitarist.talk();
    }

    public void setGuitarist(final GrammyGuitarist guitarist) {
        this.guitarist = guitarist;
    }
}
