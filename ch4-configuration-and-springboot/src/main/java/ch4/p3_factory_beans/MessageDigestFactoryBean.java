package ch4.p3_factory_beans;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.security.MessageDigest;

/*
 * In der Initialisierung (afterPropertiesSet()) wird die MessageDigest Instanz erzeugt.
 *
 * getObject() liefert das Bean, das die Factory produziert.
 *
 */
public class MessageDigestFactoryBean implements FactoryBean<MessageDigest>, InitializingBean {

    private String algorithmName = "MD5";

    private MessageDigest messageDigest = null;

    @Override
    public MessageDigest getObject() {
        return messageDigest;
    }

    @Override
    public Class<MessageDigest> getObjectType() {
        return MessageDigest.class;
    }

    @Override // initialisiert messageDigest
    public void afterPropertiesSet() throws Exception {
        this.messageDigest = MessageDigest.getInstance(algorithmName);
    }

    // informiert Spring, ob das FactoryBean ein Singleton managed
    public boolean isSingleton() {
        return true;
    }

    public void setAlgorithmName(final String algorithmName) {
        this.algorithmName = algorithmName;
    }
}
