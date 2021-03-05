package org.esia.simplecron.models

import org.esia.simplecron.utils.CronParserValidator
import org.esia.simplecron.utils.spaceDelimited

data class CronMonth(override val value: String, override val name: String = "month") : CronType(value, name) {
    private val months = 1..12
    private val monthsAlt = listOf("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC")

    init {
        require(CronParserValidator.validate(value, "([1-9]|[1][0-2]|${monthsAlt.joinToString("|")})"))
    }

    override fun expandAny(): String {
        return months.spaceDelimited()
    }

    override fun expandRange(): String {
        return value.split(CronSymbols.RANGE.actualValue).let { (rawFrom, rawTo) ->
            val from = rawFrom.toIntOrNull() ?: monthsAlt.indexOf(rawFrom) + 1
            val to = rawTo.toIntOrNull() ?: monthsAlt.indexOf(rawTo) + 1
            months.filter { month -> month in from..to }.spaceDelimited()
        }
    }

    override fun expandStep(): String {
        return value.split(CronSymbols.STEP.actualValue).let { (rawMonth, rawMonthStep) ->
            // To capture cases where the given step is of zero value
            val monthStep = rawMonthStep.toIntOrNull() ?: monthsAlt.indexOf(rawMonthStep) + 1
            if (rawMonth == CronSymbols.ANY.actualValue) {
                (months.first..months.last step monthStep).spaceDelimited()
            } else {
                val month = rawMonth.toIntOrNull() ?: monthsAlt.indexOf(rawMonth) + 1
                (month..months.last step monthStep).spaceDelimited()
            }
        }
    }
}
