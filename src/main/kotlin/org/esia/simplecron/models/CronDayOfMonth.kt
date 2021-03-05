package org.esia.simplecron.models

import org.esia.simplecron.utils.CronParserHelper
import org.esia.simplecron.utils.CronParserValidator
import org.esia.simplecron.utils.spaceDelimited

data class CronDayOfMonth(override val value: String, override val name: String = "day of month") : CronType(value, name) {
    private val days = 1..31

    init {
        require(CronParserValidator.validate(value, "([1-9]|[1-2][0-9]|[3][0-1])"))
    }

    override fun expandAny(): String {
        return days.spaceDelimited()
    }

    override fun expandRange(): String {
        return CronParserHelper.parseRangeExpression(value, days)
    }

    override fun expandStep(): String {
        return CronParserHelper.parseStepExpression(value, days)
    }
}
