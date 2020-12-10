package entry

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


internal  class entryTest {

    @Test
    fun shouldReturnCorrectMessage() {
        val message = "I must fail!!"
        assertThat(message).isEqualTo("Hello World!")
    }

}