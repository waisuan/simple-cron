package org.esia.simplecron.models

enum class CronSymbols(val actualValue: String) {
    ANY("*"),
    SEPARATOR(","),
    RANGE("-"),
    STEP("/"),
    DELIMITER(" ");

    companion object {
        fun actualValues() = values().associateBy(CronSymbols::actualValue).keys
    }
}
