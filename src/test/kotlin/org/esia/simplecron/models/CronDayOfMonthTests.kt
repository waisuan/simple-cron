package org.esia.simplecron.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CronDayOfMonthTests {
    @Test
    fun `model name is day of month`() {
        assertThat(CronDayOfMonth("*").name).isEqualTo("day of month")
    }

    @Test
    fun `expandAny returns all of the days of the month in a space-separated String`() {
        CronDayOfMonth("*").let {
            assertThat(it.expandAny()).isEqualTo((1..31).joinToString(" "))
        }
    }

    @Test
    fun `expandSeparator returns the given comma-separated values in a space-separated String`() {
        CronDayOfMonth("1,2").let {
            assertThat(it.expandSeparator()).isEqualTo("1 2")
        }

        CronDayOfMonth("1,*").let {
            assertThat(it.expandSeparator()).isEqualTo("1 ${(1..31).joinToString(" ")}")
        }

        CronDayOfMonth("1,2,3").let {
            assertThat(it.expandSeparator()).isEqualTo("1 2 3")
        }
    }

    @Test
    fun `expandRange returns a range of the given days boundary in a space-separated String`() {
        CronDayOfMonth("7-15").let {
            assertThat(it.expandRange()).isEqualTo((7..15).joinToString(" "))
        }
    }

    @Test
    fun `expandStep returns a range of days with a given gap in between each day in a space-separated String`() {
        CronDayOfMonth("5/2").let {
            assertThat(it.expandStep()).isEqualTo((5..31 step 2).joinToString(" "))
        }

        CronDayOfMonth("*/5").let {
            assertThat(it.expandStep()).isEqualTo((1..31 step 5).joinToString(" "))
        }
    }

    @Test
    fun `expandIndividual returns given value as-is`() {
        CronDayOfMonth("31").let {
            assertThat(it.expandIndividual()).isEqualTo("31")
        }
    }
}
