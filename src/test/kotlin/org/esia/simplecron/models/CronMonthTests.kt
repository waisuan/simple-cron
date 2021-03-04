package org.esia.simplecron.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CronMonthTests {
    @Test
    fun `model name is month`() {
        assertThat(CronMonth("*").name).isEqualTo("month")
    }

    @Test
    fun `expandAny returns all of the months in a space-separated String`() {
        CronMonth("*").let {
            assertThat(it.expandAny()).isEqualTo((1..12).joinToString(" "))
        }
    }

    @Test
    fun `expandSeparator returns the given comma-separated values in a space-separated String`() {
        CronMonth("1,2").let {
            assertThat(it.expandSeparator()).isEqualTo("1 2")
        }

        CronMonth("1,*").let {
            assertThat(it.expandSeparator()).isEqualTo("1 ${(1..12).joinToString(" ")}")
        }

        CronMonth("1,2,3").let {
            assertThat(it.expandSeparator()).isEqualTo("1 2 3")
        }
    }

    @Test
    fun `expandRange returns a range of the given months boundary in a space-separated String`() {
        CronMonth("7-12").let {
            assertThat(it.expandRange()).isEqualTo((7..12).joinToString(" "))
        }

        CronMonth("JAN-DEC").let {
            assertThat(it.expandRange()).isEqualTo((1..12).joinToString(" "))
        }

        CronMonth("JAN-12").let {
            assertThat(it.expandRange()).isEqualTo((1..12).joinToString(" "))
        }
    }

    @Test
    fun `expandStep returns a range of months with a given gap in between each month in a space-separated String`() {
        CronMonth("5/2").let {
            assertThat(it.expandStep()).isEqualTo((5..12 step 2).joinToString(" "))
        }

        CronMonth("*/5").let {
            assertThat(it.expandStep()).isEqualTo((1..12 step 5).joinToString(" "))
        }

        CronMonth("FEB/5").let {
            assertThat(it.expandStep()).isEqualTo((2..12 step 5).joinToString(" "))
        }

        CronMonth("MAR/FEB").let {
            assertThat(it.expandStep()).isEqualTo((3..12 step 2).joinToString(" "))
        }

        CronMonth("*/0").let {
            assertThat(it.expandStep()).isEqualTo((1..12 step 1).joinToString(" "))
        }

        CronMonth("*/JAN").let {
            assertThat(it.expandStep()).isEqualTo((1..12 step 1).joinToString(" "))
        }
    }

    @Test
    fun `expandIndividual returns given value as-is`() {
        CronMonth("12").let {
            assertThat(it.expandIndividual()).isEqualTo("12")
        }
    }
}
