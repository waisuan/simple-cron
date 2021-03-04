package org.esia.simplecron.exceptions

class UnrecognizedExpressionException(override val message: String = "Unrecognized Cron expression detected!") : Exception(message)
