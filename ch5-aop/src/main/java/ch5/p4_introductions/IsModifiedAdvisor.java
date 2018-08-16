package ch5.p4_introductions;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

/*
 * Advisor: wrap the creation of the mixin class
 *
 * This class is optional!
 */
class IsModifiedAdvisor extends DefaultIntroductionAdvisor {

    IsModifiedAdvisor() {
        super(new IsModifiedMixin());
    }
}
