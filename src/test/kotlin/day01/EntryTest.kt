package day01

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


internal class EntryTest {

    @Test
    fun shouldFindTeEntryCode() {
        val input = listOf(1721, 979, 366, 299, 675, 1456)
        assertThat(EntryCode().findFrom(input)).isEqualToComparingFieldByField(EntryCode(1721, 299))
    }

}

