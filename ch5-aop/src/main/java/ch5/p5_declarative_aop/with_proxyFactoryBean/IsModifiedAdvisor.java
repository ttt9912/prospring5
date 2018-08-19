package ch5.p5_declarative_aop.with_proxyFactoryBean;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

class IsModifiedAdvisor extends DefaultIntroductionAdvisor {

    IsModifiedAdvisor() {
        super(new IsModifiedMixin());
    }
}
