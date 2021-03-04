package org.esia.simplecron.models

import io.mockk.spyk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Test

class CronTypeTests {

    @Test
    fun `expand will call expandAny if given the ANY symbol`() {
        val mockCronType = spyk(CronMinute("*"))
        mockCronType.expand()
        verify {
            mockCronType.expandAny()
        }
    }

    @Test
    fun `expand will call expandSeparator if given the SEPARATOR symbol`() {
        var mockCronType = spyk(CronMinute("1,2"))
        mockCronType.expand()
        verify {
            mockCronType.expandSeparator()
        }

        mockCronType = spyk(CronMinute("1,2,3,4,5,6"))
        mockCronType.expand()
        verify {
            mockCronType.expandSeparator()
        }
    }

    @Test
    fun `expand will call expandRange if given the RANGE symbol`() {
        val mockCronType = spyk(CronMinute("1-2"))
        mockCronType.expand()
        verify {
            mockCronType.expandRange()
        }
    }

    @Test
    fun `expand will call expandStep if given the STEP symbol`() {
        val mockCronType = spyk(CronMinute("1/2"))
        mockCronType.expand()
        verify {
            mockCronType.expandStep()
        }
    }

    @Test
    fun `expand will call expandIndividual if given a single valid value`() {
        val mockCronType = spyk(CronMinute("1"))
        mockCronType.expand()
        verify {
            mockCronType.expandIndividual()
        }
    }

    @Test
    fun `expand will throw an exception if expression is unrecognized`() {
        val mockCronType = spyk(CronHour("######"))
        assertThatThrownBy { mockCronType.expand() }.hasMessageContaining("Unrecognized")
    }
}
