package org.esia.simplecron.models

import org.esia.simplecron.utils.CronParserHelper
import org.esia.simplecron.utils.CronParserValidator
import org.esia.simplecron.utils.spaceDelimited

data class CronHour(override val value: String, override val name: String = "hour") : CronType(value, name) {
    private val hours = 0..23

    init {
        require(CronParserValidator.validate(value, "([0-9]|[1][0-9]|[2][0-3])"))
    }

    override fun expandAny(): String {
        return hours.spaceDelimited()
    }

    override fun expandRange(): String {
        return CronParserHelper.parseRangeExpression(value, hours)
    }

    override fun expandStep(): String {
        return CronParserHelper.parseStepExpression(value, hours)
    }
}
