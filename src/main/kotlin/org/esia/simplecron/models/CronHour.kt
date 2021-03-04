package org.esia.simplecron.models

import org.esia.simplecron.utils.CronParserHelper
import org.esia.simplecron.utils.spaceDelimited

data class CronHour(override val value: String, override val name: String = "hour") : CronType(value, name) {
    private val hours = 0..23

    override fun expandAny(): String {
        return hours.spaceDelimited()
    }

    override fun expandRange(): String {
        return CronParserHelper.rangeExpressionParser(value, hours)
    }

    override fun expandStep(): String {
        return CronParserHelper.stepExpressionParser(value, hours)
    }
}
