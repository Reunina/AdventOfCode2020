package day01

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


internal class EntryTest {

    @Test
    fun shouldFindTheEntryCode() {
        val input = listOf(1721, 979, 366, 299, 675, 1456)
        assertThat(EntryCode().findFromTwoEntriesOf(input))
                .isEqualToComparingFieldByField(EntryCode(1721, 299))
                .hasFieldOrPropertyWithValue("code", 514579)
    }

    @Test
    fun shouldFindTheContinuationCode() {
        val input = listOf(1721, 979, 366, 299, 675, 1456)
        assertThat(EntryCode().findFromThreeEntriesOf(input))
                .isEqualToComparingFieldByField(EntryCode(979, 366, 675))
                .hasFieldOrPropertyWithValue("code", 241861950)
    }

}
