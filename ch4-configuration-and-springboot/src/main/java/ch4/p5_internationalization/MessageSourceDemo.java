package ch4.p5_internationalization;

import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Locale;

/*
 * MessageSource implementations:
 * - ApplicationContext
 * - ResourceBundleMessageSource and ReloadableResourceBundleMessageSource
 * - StaticMessageSource
 *
 * ApplicationContext als MessageSource koppelt Beans unnötig an den ApplicationContext.
 */
class MessageSourceDemo {

    // ApplicationContext als MessageSource sollte nur in WebApplications verwendet werden.
    @Test
    void i18n_withApplicationContextAsMessageSource() {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("internationalization_context.xml");
        ctx.refresh();

        Locale english = Locale.ENGLISH;
        Locale german = new Locale("de", "DE");

        // Einfache Messages
        System.out.println(ctx.getMessage("msg", null, english));
        System.out.println(ctx.getMessage("msg", null, german));

        // Parametrisierbare Messages
        System.out.println(ctx.getMessage("nameMsg", new Object[]{"John", "Mayer"}, english));
        System.out.println(ctx.getMessage("nameMsg", new Object[]{"John", "Mayer"}, german));

        ctx.close();
    }
}
