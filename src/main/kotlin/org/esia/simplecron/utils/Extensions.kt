package org.esia.simplecron.utils

import org.esia.simplecron.models.CronSymbols

fun <T> Collection<T>.spaceDelimited(): String = this.joinToString(CronSymbols.DELIMITER.actualValue)
fun IntRange.spaceDelimited(): String = this.joinToString(CronSymbols.DELIMITER.actualValue)
fun IntProgression.spaceDelimited(): String = this.joinToString(CronSymbols.DELIMITER.actualValue)
