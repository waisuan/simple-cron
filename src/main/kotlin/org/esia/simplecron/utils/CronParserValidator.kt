package org.esia.simplecron.utils

object CronParserValidator {
    private const val anyPattern = "^\\*$"
    private const val alphaNumericSeparatorPattern = "^([A-Z0-9]+|\\*)(,([A-Z0-9]+|\\*))+$"
    private const val alphaNumericRangePattern = "^[A-Z0-9]+-[A-Z0-9]+$"
    private const val alphaNumericStepPattern = "^([A-Z0-9]+|\\*)/[A-Z0-9]+$"
    private const val alphaNumericPattern = "^[A-Z0-9]+$"

    private const val separatorPattern = "^(%s|\\*)(,(%s|\\*))+$"
    private const val rangePatten = "^%s-%s$"
    private const val stepPattern = "^(%s|\\*)/%s$"
    private const val individualPattern = "^%s$"

    fun validate(subExpression: String, allowedValuePattern: String): Boolean {
        return Regex("$anyPattern|" +
            "${separatorPattern.format(allowedValuePattern, allowedValuePattern)}|" +
            "${rangePatten.format(allowedValuePattern, allowedValuePattern)}|" +
            "${stepPattern.format(allowedValuePattern, allowedValuePattern)}|" +
            individualPattern.format(allowedValuePattern)
        ).matches(subExpression)
    }

    fun isSeparatorPattern(subExpression: String): Boolean {
        return Regex(alphaNumericSeparatorPattern).matches(subExpression)
    }

    fun isRangePattern(subExpression: String): Boolean {
        return Regex(alphaNumericRangePattern).matches(subExpression)
    }

    fun isStepPattern(subExpression: String): Boolean {
        return Regex(alphaNumericStepPattern).matches(subExpression)
    }

    fun isIndividualPattern(subExpression: String): Boolean {
        return Regex(alphaNumericPattern).matches(subExpression)
    }
}
