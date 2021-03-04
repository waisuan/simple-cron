package org.esia.simplecron.models

import org.esia.simplecron.exceptions.UnrecognizedExpressionException
import org.esia.simplecron.utils.CronParserHelper
import org.esia.simplecron.utils.CronParserValidator
import org.esia.simplecron.utils.spaceDelimited

data class CronMinute(override val value: String, override val name: String = "minute") : CronType(value, name) {
    private val minutes = 0..59

    init {
        // TODO: With the interest of time, the following is to showcase how/where we'd implement a validation layer on top
        // of these Cron models. In this case, we'd need to extent it further to cover more cases.
        CronParserValidator.validateNumeric(value).let { matchResult ->
            if (!matchResult) {
                throw UnrecognizedExpressionException()
            }
        }
    }

    override fun expandAny(): String {
        return minutes.spaceDelimited()
    }

    override fun expandRange(): String {
        return CronParserHelper.rangeExpressionParser(value, minutes)
    }

    override fun expandStep(): String {
        return CronParserHelper.stepExpressionParser(value, minutes)
    }
}
