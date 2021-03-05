package org.esia.simplecron.utils

import org.esia.simplecron.models.CronSymbols

object CronParserHelper {
    fun parseRangeExpression(subExpression: String, allowedValues: IntRange): String {
        return subExpression.split(CronSymbols.RANGE.actualValue).let { (from, to) ->
            allowedValues.filter { value -> value in from.toInt()..to.toInt() }.spaceDelimited()
        }
    }

    fun parseStepExpression(subExpression: String, allowedValues: IntRange): String {
        return subExpression.split(CronSymbols.STEP.actualValue).let { (value, step) ->
            if (value == CronSymbols.ANY.actualValue) {
                (allowedValues.first..allowedValues.last step step.toInt()).spaceDelimited()
            } else {
                (value.toInt()..allowedValues.last step step.toInt()).spaceDelimited()
            }
        }
    }
}
