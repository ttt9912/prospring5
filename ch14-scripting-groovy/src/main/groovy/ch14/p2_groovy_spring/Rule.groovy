package ch14.p2_groovy_spring

/*
 * each rule has several properties
 *
 * - conditions: conditions that the rule engine should check
 *               for the domain object
 * - actions: actions to take when a match the condition is hit
 * - parameters: behaviour of the rule, outcome of the action
 *               for different conditions
 * - singlehit: rule should end its execution whenever a match
 *              of condition is found
 */

class Rule {
    private boolean singlehit = true
    private conditions = new ArrayList()
    private actions = new ArrayList()
    private parameters = new ArrayList()
}
