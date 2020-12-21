package com.adventofcode.day07

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.util.Arrays
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import org.junit.jupiter.api.assertAll

internal class RuleTest {

    @Test
    fun shouldDisplayNicely() {
        assertThat(Rule("A", "1 B", "3 C"))
                .hasToString("[A] contains {B=1, C=3}")
    }

    @Test
    fun shouldAuthorized0OrMoreInnerBags() {

        assertAll(
                {
                    assertThat(Rule("A")).hasToString("[A] contains no other bag")
                    assertThat(Rule("A" , "1 B" )).hasToString("[A] contains {B=1}")
                    assertThat(Rule("A" , "1 B" , "2 C")).hasToString("[A] contains {B=1, C=2}")
                    assertThat(Rule("A" , "3 D", "1 B" , "2 C")).hasToString("[A] contains {D=3, B=1, C=2}")
                }
        )
    }


    @TestFactory
    fun shouldReadRuleFromInput() = listOf(
            "light red bags contain 1 bright white bag, 2 muted yellow bags." to Pair("light red", Arrays.array( "1 bright white", "2 muted yellow")),
            "dark orange bags contain 3 bright white bags, 4 muted yellow bags." to Pair("dark orange",  Arrays.array( "3 bright white", "4 muted yellow")),
            "dark orange bags contain 3 bright white bags, 1 muted yellow bag." to Pair("dark orange", Arrays.array(  "3 bright white", "1 muted yellow")),
            "bright white bags contain 1 shiny gold bag." to Pair("bright white", Arrays.array(  "1 shiny gold", "")),
            "faded blue bags contain no other bags." to Pair("faded blue", Arrays.array(  "")),
            "posh brown bags contain 5 dim coral bags, 1 plaid blue bag, 2 faded bronze bags, 2 light black bags." to Pair("posh brown", Arrays.array(  "5 dim coral", "1 plaid blue", "2 faded bronze", "2 light black")))
            .map { (ruleAsInput, expectedValues) ->
                DynamicTest.dynamicTest("should read Rule for ${expectedValues.first} contains ${expectedValues.second}") {
                    assertThat(Rule.from(ruleAsInput))
                            .isEqualToComparingFieldByFieldRecursively(Rule(expectedValues.first, *expectedValues.second))
                }
            }


    @Test
    fun shouldCompareTwoRules() {

        assertThat(Rule("A", "1 B", "2 C"))
                .isEqualTo(Rule("A", "1 B", "2 C"))
                .isNotEqualTo(Rule("A", "1 A", "2 C"))
                .isNotEqualTo(Rule("A", "1 A", "1 A"))
                .isNotEqualTo(Rule("B", "1 A", "2 C"))
                .isNotEqualTo("Other object")
    }
}