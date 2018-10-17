package ch14.p2_groovy_spring

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

/*
 * each condition is a closure, each action is a closure
 */

@Component("ruleEngine")
class RuleEngineImpl implements RuleEngine {
    Logger logger = LoggerFactory.getLogger(RuleEngineImpl.class)

    @Override
    void run(final Rule rule, final Object domainObject) {
        logger.info "executing rule"

        def exit = false

        rule.parameters.each { ArrayList params ->
            def paramIndex = 0
            def success = true

            if (!exit) {
                rule.conditions.each {
                    logger.info "condition param index: " + paramIndex
                    success = success && it(domainObject, params[paramIndex])
                    logger.info "condition success " + success
                    paramIndex++
                }

                if (success && !exit) {
                    rule.actions.each {
                        logger.info "action param index: " + paramIndex
                        it(domainObject, params[paramIndex])
                        paramIndex++
                    }

                    if (rule.singlehit) {
                        exit = true;
                    }
                }
            }
        }
    }
}
