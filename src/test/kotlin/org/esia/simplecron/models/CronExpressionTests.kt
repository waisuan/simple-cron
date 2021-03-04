package org.esia.simplecron.models

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class CronExpressionTests {
    @Test
    fun `throws an exception if expression does not contain exactly 5 sub-expression values`() {
        assertThatThrownBy { CronExpression("1 2 3 4 5 6", "") }.hasMessageContaining("Unrecognized")
        assertThatThrownBy { CronExpression("", "") }.hasMessageContaining("Unrecognized")
    }

    @Test
    fun `toFormattedOutput returns the given expression in formatted expanded form`() {
        assertThat(CronExpression("*/15 0 1,15 * 1-5", "/usr/bin/find").toFormattedOutput())
            .isEqualTo("""
                minute        0 15 30 45
                hour          0
                day of month  1 15
                month         1 2 3 4 5 6 7 8 9 10 11 12
                day of week   1 2 3 4 5
                command       /usr/bin/find
            """.trimIndent())
    }
}
