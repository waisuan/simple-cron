package org.esia.simplecron.models

import kotlin.math.max
import org.esia.simplecron.utils.CronParserValidator
import org.esia.simplecron.utils.spaceDelimited

data class CronDayOfWeek(override val value: String, override val name: String = "day of week") : CronType(value, name) {
    private val days = 0..6
    private val daysAlt = listOf("SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT")

    init {
        require(CronParserValidator.validate(value, "([0-6]|${daysAlt.joinToString("|")})"))
    }

    override fun expandAny(): String {
        return days.spaceDelimited()
    }

    override fun expandRange(): String {
        return value.split(CronSymbols.RANGE.actualValue).let { (rawFrom, rawTo) ->
            val from = rawFrom.toIntOrNull() ?: daysAlt.indexOf(rawFrom)
            val to = rawTo.toIntOrNull() ?: daysAlt.indexOf(rawTo)
            days.filter { month -> month in from..to }.spaceDelimited()
        }
    }

    override fun expandStep(): String {
        return value.split(CronSymbols.STEP.actualValue).let { (rawDay, rawDayStep) ->
            // To capture cases where the given step is a SUNDAY i.e. zero-indexed value
            val dayStep = max(rawDayStep.toIntOrNull() ?: daysAlt.indexOf(rawDayStep), 1)
            if (rawDay == CronSymbols.ANY.actualValue) {
                (days.first..days.last step dayStep).spaceDelimited()
            } else {
                val day = rawDay.toIntOrNull() ?: daysAlt.indexOf(rawDay)
                (day..days.last step dayStep).spaceDelimited()
            }
        }
    }
}
