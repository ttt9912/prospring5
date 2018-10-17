package ch14.p2_groovy_spring;

/*
 * applies the rule to the domain object argument
 *
 * implementation is in groovy
 */
public interface RuleEngine {
    void run(Rule rule, Object domainObject);
}
