package org.esia.simplecron.models

import org.esia.simplecron.utils.CronParserHelper
import org.esia.simplecron.utils.CronParserValidator
import org.esia.simplecron.utils.spaceDelimited

data class CronMinute(override val value: String, override val name: String = "minute") : CronType(value, name) {
    private val minutes = 0..59

    init {
        require(CronParserValidator.validate(value, "([0-9]|[1-5][0-9])"))
    }

    override fun expandAny(): String {
        return minutes.spaceDelimited()
    }

    override fun expandRange(): String {
        return CronParserHelper.parseRangeExpression(value, minutes)
    }

    override fun expandStep(): String {
        return CronParserHelper.parseStepExpression(value, minutes)
    }
}
