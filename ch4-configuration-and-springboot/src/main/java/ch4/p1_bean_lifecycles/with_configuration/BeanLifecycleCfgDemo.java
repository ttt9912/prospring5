package ch4.p1_bean_lifecycles.with_configuration;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/*
 * Destroy auslösen:
 *   #1: ConfigurableListableBeanFactory.destroySingletons(); destroys singleton beans (cleanup)
 *   #2: ctx.registerShutdownHook(); // calls destroySingletons() in a separate thread (mainly used for standalone apps)
 */
class BeanLifecycleCfgDemo {

    @Test
    void withConfiguration(){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SingerConfig.class);

        Singer singerOne = ctx.getBean("singerOne", Singer.class);
        Singer singerTwo = ctx.getBean("singerTwo", Singer.class);
        Singer singerThree = ctx.getBean("singerThree", Singer.class);

        ctx.getBeanFactory().destroySingletons();

        ctx.close();
    }
}
