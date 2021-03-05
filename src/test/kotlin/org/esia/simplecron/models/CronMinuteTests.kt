package org.esia.simplecron.models

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class CronMinuteTests {
    @Test
    fun `model name is minute`() {
        assertThat(CronMinute("*").name).isEqualTo("minute")
    }

    @Test
    fun `expandAny returns all of the minutes in a space-separated String`() {
        CronMinute("*").let {
            assertThat(it.expandAny()).isEqualTo((0..59).joinToString(" "))
        }
    }

    @Test
    fun `expandSeparator returns the given comma-separated values in a space-separated String`() {
        CronMinute("1,2").let {
            assertThat(it.expandSeparator()).isEqualTo("1 2")
        }

        CronMinute("1,*").let {
            assertThat(it.expandSeparator()).isEqualTo("1 ${(0..59).joinToString(" ")}")
        }

        CronMinute("1,2,3").let {
            assertThat(it.expandSeparator()).isEqualTo("1 2 3")
        }
    }

    @Test
    fun `expandRange returns a range of the given minutes boundary in a space-separated String`() {
        CronMinute("7-15").let {
            assertThat(it.expandRange()).isEqualTo((7..15).joinToString(" "))
        }
    }

    @Test
    fun `expandStep returns a range of minutes with a given gap in between each minute in a space-separated String`() {
        CronMinute("5/2").let {
            assertThat(it.expandStep()).isEqualTo((5..59 step 2).joinToString(" "))
        }

        CronMinute("*/5").let {
            assertThat(it.expandStep()).isEqualTo((0..59 step 5).joinToString(" "))
        }
    }

    @Test
    fun `expandIndividual returns given value as-is`() {
        CronMinute("59").let {
            assertThat(it.expandIndividual()).isEqualTo("59")
        }
    }

    @Test
    fun `throws an exception if given an unrecognized minute pattern`() {
        assertThatThrownBy { CronMinute("100") }.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { CronMinute("-1") }.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { CronMinute("A") }.isInstanceOf(IllegalArgumentException::class.java)
        assertThatThrownBy { CronMinute("-1,100") }.isInstanceOf(IllegalArgumentException::class.java)
    }
}
