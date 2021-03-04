package org.esia.simplecron.models

import org.esia.simplecron.utils.CronParserHelper
import org.esia.simplecron.utils.spaceDelimited

data class CronDayOfMonth(override val value: String, override val name: String = "day of month") : CronType(value, name) {
    private val days = 1..31

    override fun expandAny(): String {
        return days.spaceDelimited()
    }

    override fun expandRange(): String {
        return CronParserHelper.rangeExpressionParser(value, days)
    }

    override fun expandStep(): String {
        return CronParserHelper.stepExpressionParser(value, days)
    }
}
