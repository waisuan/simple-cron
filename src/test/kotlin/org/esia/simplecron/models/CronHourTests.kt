package org.esia.simplecron.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CronHourTests {
    @Test
    fun `model name is hour`() {
        assertThat(CronHour("*").name).isEqualTo("hour")
    }

    @Test
    fun `expandAny returns all of the hours in a space-separated String`() {
        CronHour("*").let {
            assertThat(it.expandAny()).isEqualTo((0..23).joinToString(" "))
        }
    }

    @Test
    fun `expandSeparator returns the given comma-separated values in a space-separated String`() {
        CronHour("1,2").let {
            assertThat(it.expandSeparator()).isEqualTo("1 2")
        }

        CronHour("1,*").let {
            assertThat(it.expandSeparator()).isEqualTo("1 ${(0..23).joinToString(" ")}")
        }

        CronHour("1,2,3").let {
            assertThat(it.expandSeparator()).isEqualTo("1 2 3")
        }
    }

    @Test
    fun `expandRange returns a range of the given hours boundary in a space-separated String`() {
        CronHour("7-15").let {
            assertThat(it.expandRange()).isEqualTo((7..15).joinToString(" "))
        }
    }

    @Test
    fun `expandStep returns a range of hours with a given gap in between each hour in a space-separated String`() {
        CronHour("5/2").let {
            assertThat(it.expandStep()).isEqualTo((5..23 step 2).joinToString(" "))
        }

        CronHour("*/5").let {
            assertThat(it.expandStep()).isEqualTo((0..23 step 5).joinToString(" "))
        }
    }

    @Test
    fun `expandIndividual returns given value as-is`() {
        CronHour("23").let {
            assertThat(it.expandIndividual()).isEqualTo("23")
        }
    }
}
