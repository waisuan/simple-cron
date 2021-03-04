package org.esia.simplecron.models

import org.esia.simplecron.exceptions.UnrecognizedExpressionException

data class CronExpression(val expression: String, val unixCommand: String) {
    private var cronMinute: CronMinute
    private var cronHour: CronHour
    private var cronDayOfMonth: CronDayOfMonth
    private var cronMonth: CronMonth
    private var cronDayOfWeek: CronDayOfWeek

    private val outputRowFormat = "%-14s%s"

    init {
        expression.split(CronSymbols.DELIMITER.actualValue).let {
            if (it.size != 5)
                throw UnrecognizedExpressionException()
            cronMinute = CronMinute(it.first())
            cronHour = CronHour(it[1])
            cronDayOfMonth = CronDayOfMonth(it[2])
            cronMonth = CronMonth(it[3])
            cronDayOfWeek = CronDayOfWeek(it.last())
        }
    }

    fun toFormattedOutput(): String {
        return listOf(cronMinute, cronHour, cronDayOfMonth, cronMonth, cronDayOfWeek)
            .joinToString("\n", postfix = "\n") { String.format(outputRowFormat, it.name, it.expand()) }
            .plus(String.format(outputRowFormat, "command", unixCommand))
    }
}
