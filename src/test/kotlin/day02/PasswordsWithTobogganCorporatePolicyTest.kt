package day02

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class PasswordsWithTobogganCorporatePolicyTest {

    private val tobogganPolicyFromInput = { policyAsString: String -> TobogganCorporatePolicy.fromInput(policyAsString) }

    @Test
    fun passwordIsInvalidWhenBothPositionsContainTheKeyLetter() {
        PasswordAssert.assertThatPassword(Password.fromInput(tobogganPolicyFromInput,"2-9 c: ccccccccc"))
                .isEqualToComparingFieldByFieldRecursively(
                        Password("ccccccccc", tobogganPolicyFromInput("2-9 c"))
                )
                .isInValid()
    }

    @Test
    fun passwordIsInvalidWhenNeitherPositionsContainTheKeyLetter() {
        PasswordAssert.assertThatPassword(Password.fromInput(tobogganPolicyFromInput,"1-3 b: cdefg"))
                .isEqualToComparingFieldByFieldRecursively(
                        Password("cdefg", tobogganPolicyFromInput("1-3 b"))
                )
                .isInValid()
    }

    @Test
    fun passwordIsValidWhenExactlyOnePositionsContainTheKeyLetter() {
        PasswordAssert.assertThatPassword(Password.fromInput(tobogganPolicyFromInput,"1-3 a: abcde"))
                .`as`("1-3 a: abcde must be valid: position 1 contains a and position 3 does not.")
                .isEqualToComparingFieldByFieldRecursively(
                        Password("abcde", tobogganPolicyFromInput( "1-3 a"))
                )
                .isValid()

        PasswordAssert.assertThatPassword(Password.fromInput(tobogganPolicyFromInput,"1-3 a: xbade"))
                .`as`("1-3 a: xbade must be valid: position 3 contains a and position 1 does not.")
                .isEqualToComparingFieldByFieldRecursively(
                        Password("xbade", tobogganPolicyFromInput( "1-3 a"))
                )
                .isValid()
    }

    @Test
    fun allValidPasswordsCanBeFound() {
        val passwordsAndPoliciesAsString = listOf("1-3 a: abcde", "2-9 c: ccccccccc", "1-3 b: cdefg")
        Assertions.assertThat(
                Passwords(passwordsAndPoliciesAsString).findAllValidForPolicy(tobogganPolicyFromInput)
        )
                .hasSize(1)
                .usingRecursiveFieldByFieldElementComparator()
                .containsOnly(
                        Password.fromInput(tobogganPolicyFromInput,"1-3 a: abcde"))
    }

}
