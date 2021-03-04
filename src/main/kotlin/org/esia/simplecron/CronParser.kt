package org.esia.simplecron

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody
import org.esia.simplecron.internal.CommandLineParser
import org.esia.simplecron.models.CronExpression

fun main(args: Array<String>) = mainBody {
    // Note: command line arg are parsed using a custom-made parser --> CommandLineParser.kt
    ArgParser(args).parseInto(::CommandLineParser).run {
        println(CronExpression(cronExpression, unixCommand).toFormattedOutput())
    }
}
