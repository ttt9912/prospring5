package ch5.p5_declarative_aop.with_aopNamespace;

public class NewDocumentarist extends Documentarist {

    @Override
    public void execute() {
        guitarist.sing();
        guitarist.sing(new Guitar());
        guitarist.talk();
    }
}
