package day02

import day02.PasswordAssert.Companion.assertThatPassword
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


internal class PasswordsTest {

    @Test
    fun passwordWhichRespectItsPolicyIsValid() {
        assertThatPassword(Password.fromInput("1-3 a: abcde"))
                .isEqualToComparingFieldByFieldRecursively(Password("abcde").withSledRentalPolicy("1-3", "a"))
                .isValid()
        assertThatPassword(Password.fromInput("2-9 c: ccccccccc"))
                .isEqualToComparingFieldByFieldRecursively(Password("ccccccccc").withSledRentalPolicy("2-9", "c"))
                .isValid()
    }

    @Test
    fun passwordWhichDoesNotRespectItsPolicyIsInvalid() {
        assertThatPassword(Password.fromInput("1-3 b: cdefg"))
                .isEqualToComparingFieldByFieldRecursively(Password("cdefg").withSledRentalPolicy("1-3", "b"))
                .isInValid()
    }

    @Test
    fun allValidPasswordsCanBeFound() {
        val passwordsAsString = listOf("1-3 a: abcde", "2-9 c: ccccccccc", "1-3 b: cdefg")
        assertThat(Passwords(passwordsAsString).findAllValid())
                .hasSize(2)
                .usingRecursiveFieldByFieldElementComparator()
                .containsOnly(
                        Password.fromInput("1-3 a: abcde"),
                        Password.fromInput("2-9 c: ccccccccc"))
    }

}

