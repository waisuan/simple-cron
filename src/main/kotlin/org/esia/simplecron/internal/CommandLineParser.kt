package org.esia.simplecron.internal

import com.xenomachina.argparser.ArgParser

class CommandLineParser(parser: ArgParser) {
    val cronExpression by parser.positional("CRON_EXPRESSION", help = "Cron schedule expression e.g. minute hour day_of_month month day_of_week")
    val unixCommand by parser.positional("UNIX_COMMAND", help = "Unix command")
}
