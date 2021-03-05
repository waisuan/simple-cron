package org.esia.simplecron.models

import org.esia.simplecron.exceptions.UnrecognizedExpressionException
import org.esia.simplecron.utils.CronParserValidator

abstract class CronType(open val value: String, open val name: String) {
    abstract fun expandAny(): String

    open fun expandSeparator(): String {
        return value.split(CronSymbols.SEPARATOR.actualValue).let { values ->
            values.joinToString(CronSymbols.DELIMITER.actualValue) {
                if (it == CronSymbols.ANY.actualValue)
                    expandAny()
                else
                    it
            }
        }
    }

    abstract fun expandRange(): String
    abstract fun expandStep(): String

    open fun expandIndividual(): String {
        return value
    }

    fun expand(): String {
        return when {
            value == CronSymbols.ANY.actualValue -> expandAny()
            CronParserValidator.isSeparatorPattern(value) -> expandSeparator()
            CronParserValidator.isRangePattern(value) -> expandRange()
            CronParserValidator.isStepPattern(value) -> expandStep()
            CronParserValidator.isIndividualPattern(value) -> expandIndividual()
            else -> throw UnrecognizedExpressionException()
        }
    }
}
