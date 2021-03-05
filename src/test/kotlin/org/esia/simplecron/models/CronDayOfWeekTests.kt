package org.esia.simplecron.models

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CronDayOfWeekTests {
    @Test
    fun `model name is day of week`() {
        assertThat(CronDayOfWeek("*").name).isEqualTo("day of week")
    }

    @Test
    fun `expandAny returns all of the months in a space-separated String`() {
        CronDayOfWeek("*").let {
            assertThat(it.expandAny()).isEqualTo((0..6).joinToString(" "))
        }
    }

    @Test
    fun `expandSeparator returns the given comma-separated values in a space-separated String`() {
        CronDayOfWeek("1,2").let {
            assertThat(it.expandSeparator()).isEqualTo("1 2")
        }

        CronDayOfWeek("1,*").let {
            assertThat(it.expandSeparator()).isEqualTo("1 ${(0..6).joinToString(" ")}")
        }

        CronDayOfWeek("1,2,3").let {
            assertThat(it.expandSeparator()).isEqualTo("1 2 3")
        }
    }

    @Test
    fun `expandRange returns a range of the given months boundary in a space-separated String`() {
        CronDayOfWeek("2-5").let {
            assertThat(it.expandRange()).isEqualTo((2..5).joinToString(" "))
        }

        CronDayOfWeek("MON-WED").let {
            assertThat(it.expandRange()).isEqualTo((1..3).joinToString(" "))
        }

        CronDayOfWeek("MON-3").let {
            assertThat(it.expandRange()).isEqualTo((1..3).joinToString(" "))
        }
    }

    @Test
    fun `expandStep returns a range of months with a given gap in between each month in a space-separated String`() {
        CronDayOfWeek("1/2").let {
            assertThat(it.expandStep()).isEqualTo((1..6 step 2).joinToString(" "))
        }

        CronDayOfWeek("*/5").let {
            assertThat(it.expandStep()).isEqualTo((0..6 step 5).joinToString(" "))
        }

        CronDayOfWeek("SUN/5").let {
            assertThat(it.expandStep()).isEqualTo((0..6 step 5).joinToString(" "))
        }

        CronDayOfWeek("SUN/TUE").let {
            assertThat(it.expandStep()).isEqualTo((0..6 step 2).joinToString(" "))
        }

        CronDayOfWeek("*/0").let {
            assertThat(it.expandStep()).isEqualTo((0..6 step 1).joinToString(" "))
        }

        CronDayOfWeek("*/SUN").let {
            assertThat(it.expandStep()).isEqualTo((0..6 step 1).joinToString(" "))
        }
    }

    @Test
    fun `expandIndividual returns given value as-is`() {
        CronDayOfWeek("6").let {
            assertThat(it.expandIndividual()).isEqualTo("6")
        }
    }

    @Test
    fun `throws an exception if given an unrecognized day of week pattern`() {
        Assertions.assertThatThrownBy { CronDayOfWeek("7") }.isInstanceOf(IllegalArgumentException::class.java)
        Assertions.assertThatThrownBy { CronDayOfWeek("-1") }.isInstanceOf(IllegalArgumentException::class.java)
        Assertions.assertThatThrownBy { CronDayOfWeek("ABC") }.isInstanceOf(IllegalArgumentException::class.java)
        Assertions.assertThatThrownBy { CronDayOfWeek("-1,100") }.isInstanceOf(IllegalArgumentException::class.java)
    }
}
