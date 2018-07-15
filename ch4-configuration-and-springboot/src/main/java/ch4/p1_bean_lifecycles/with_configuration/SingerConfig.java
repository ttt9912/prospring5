package ch4.p1_bean_lifecycles.with_configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/*
 * initMethod: analog zu init-method Attribut
 * destroyMethod: analog zu destroy-method Attribut
 * @Lazy: analog zu default-lazy-init="true"
 */
@Configuration
class SingerConfig {

    @Lazy
    @Bean(initMethod = "init", destroyMethod = "destroy")
    Singer singerOne() {
        Singer singerOne = new Singer();
        singerOne.setName("John Mayer");
        singerOne.setAge(39);
        return singerOne;
    }

    @Lazy
    @Bean(initMethod = "init", destroyMethod = "destroy")
    Singer singerTwo() {
        Singer singerOne = new Singer();
        singerOne.setAge(39);
        return singerOne;
    }

    @Lazy
    @Bean(initMethod = "init", destroyMethod = "destroy")
    Singer singerThree() {
        Singer singerOne = new Singer();
        singerOne.setName("John Mayer");
        return singerOne;
    }
}
