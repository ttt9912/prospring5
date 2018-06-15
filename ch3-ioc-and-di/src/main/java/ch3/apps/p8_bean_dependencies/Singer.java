package ch3.apps.p8_bean_dependencies;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/*
 * Singers Methode sing() braucht das Guitar Bean (fender).
 * Daher muss das Guitar Bean vor dem Singer Bean instanziiert werden.
 *
 * Singer implementiert ApplicationContextAware um Zugriff auf den aktuellen
 * ApplicationContext zu erhalten.
 */
class Singer implements ApplicationContextAware{
    ApplicationContext ctx;

    private Guitar guitar;

    public void sing(){
        guitar = ctx.getBean("fender", Guitar.class);
        guitar.play();
    }

    @Override // wird von Spring nach dem Singer-Konstruktor aufgerufen.
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }
}
