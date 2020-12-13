package com.adventofcode.day02

import com.adventofcode.day02.PasswordAssert.Companion.assertThatPassword
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


internal class PasswordsWithSledRentalPolicyTest {

    private val sledRentalPolicyFromInput = { policyAsString: String -> SledRentalPolicy.fromInput(policyAsString) }

    @Test
    fun passwordWhichRespectItsPolicyIsValid() {
        assertThatPassword(Password.fromInput(sledRentalPolicyFromInput, "1-3 a: abcde"))
                .isEqualToComparingFieldByFieldRecursively(
                        Password("abcde", sledRentalPolicyFromInput("1-3 a"))
                )
                .isValid()
        assertThatPassword(Password.fromInput(sledRentalPolicyFromInput, "2-9 c: ccccccccc"))
                .isEqualToComparingFieldByFieldRecursively(
                        Password("ccccccccc", sledRentalPolicyFromInput("2-9 c"))
                )
                .isValid()
    }

    @Test
    fun passwordWhichDoesNotRespectItsPolicyIsInvalid() {
        assertThatPassword(Password.fromInput(sledRentalPolicyFromInput, "1-3 b: cdefg"))
                .isEqualToComparingFieldByFieldRecursively(
                        Password("cdefg", sledRentalPolicyFromInput("1-3 b"))
                )
                .isInValid()
    }

    @Test
    fun allValidPasswordsCanBeFound() {
        val passwordsAsString = listOf("1-3 a: abcde", "2-9 c: ccccccccc", "1-3 b: cdefg")
        assertThat(Passwords(passwordsAsString).findAllValidForPolicy(sledRentalPolicyFromInput))
                .hasSize(2)
                .usingRecursiveFieldByFieldElementComparator()
                .containsOnly(
                        Password.fromInput(sledRentalPolicyFromInput, "1-3 a: abcde"),
                        Password.fromInput(sledRentalPolicyFromInput, "2-9 c: ccccccccc"))
    }

}

