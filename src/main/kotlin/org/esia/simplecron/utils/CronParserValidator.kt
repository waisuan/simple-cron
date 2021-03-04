package org.esia.simplecron.utils

object CronParserValidator {
    val anyPattern = "^\\*$"
    val alphaNumericSeparatorPattern = "^([A-Z0-9]+|\\*)(,([A-Z0-9]+|\\*))+$"
    val alphaNumericRangePattern = "^[A-Z0-9]+-[A-Z0-9]+$"
    val alphaNumericStepPattern = "^([A-Z0-9]+|\\*)/[A-Z0-9]+$"
    val alphaNumericPattern = "^[A-Z0-9]+$"

    val numericSeparatorPattern = "^([0-9]+|\\*)(,([0-9]+|\\*))+$"
    val numericRangePattern = "^[0-9]+-[0-9]+$"
    val numericStepPattern = "^([0-9]+|\\*)/[0-9]+$"
    val numericPattern = "^\\d+$"

    fun validateNumeric(subExpression: String): Boolean {
        return Regex("$anyPattern|$numericSeparatorPattern|$numericRangePattern|$numericStepPattern|$numericPattern").matches(subExpression)
    }

    fun validateAlphaNumeric(subExpression: String): Boolean {
        return Regex("$anyPattern|$alphaNumericSeparatorPattern|$alphaNumericRangePattern|$alphaNumericStepPattern|$alphaNumericPattern").matches(subExpression)
    }
}
