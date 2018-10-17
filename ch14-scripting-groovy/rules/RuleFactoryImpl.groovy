import ch14.p2_groovy_spring.Rule
import ch14.p2_groovy_spring.RuleFactory
import org.springframework.stereotype.Component

import java.time.LocalDate
import java.time.temporal.ChronoUnit

/*
 * Refreshable bean: spring bean written in a scripting language
 *
 * to allow dynamic refresh, this is placed in an external folder
 */

@Component
class RuleFactoryImpl implements RuleFactory {

    Closure age = { birthDate ->
        return ChronoUnit.YEARS.between(birthDate, LocalDate.now())
    }

    @Override
    Rule getAgeCategoryRule() {
        Rule rule = new Rule()
        rule.singlehit = true

        // check if age is greater or smaller than parameter
        rule.conditions = [{ object, param -> age(object.birthDate) >= param },
                           { object, param -> age(object.birthDate) <= param }]

        // assign value provided in the parameter to the ageCategory
        rule.actions = [{object, param -> object.ageCategory = param}]

        // defines parameters for both conditions checking and action
        rule.parameters = [
                [0, 10, 'Kid'],
                [11, 20, 'Youth'],
                [21, 40, 'Adult'],
                [41, 60, 'pre Matured'],
                [61, 80, 'Middle-aged'],
                [81, 120, 'Old']
        ]

        return rule
    }
}
