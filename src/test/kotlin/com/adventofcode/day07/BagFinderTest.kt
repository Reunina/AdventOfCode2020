package com.adventofcode.day07

import com.adventofcode.day07.BagFinderAssert.Companion.assertThatBagFinder
import org.assertj.core.api.AbstractObjectAssert
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class BagFinderTest {

    private val rule = Rule("light red", "1 bright white", "2 muted yellow")

    @Test
    fun shouldFindNumberOfBagsThatCanContainsASpecificColoredBag() {
        val input = "posh brown bags contain 5 dim coral bags, 1 plaid blue bag, 2 faded bronze bags, 2 light black bags.\n" +
                "vibrant lime bags contain 3 dull lavender bags, 3 dim crimson bags, 3 mirrored lavender bags, 2 muted cyan bags.\n" +
                "clear olive bags contain 1 wavy gold bag, 4 dim lime bags, 3 dull tomato bags, 5 dark turquoise bags.\n" +
                "dark purple bags contain 5 striped tan bags, 5 bright cyan bags, 3 dark indigo bags.\n" +
                "posh maroon bags contain 3 bright salmon bags."
        assertThatBagFinder(BagFinder(input))
                .hasReadRule(Rule("posh brown", "5 dim coral", "1 plaid blue", "2 faded bronze", "2 light black"))
    }

    @Test
    fun shouldBeAbleToReadOneRuleFromInput() {
        assertThatBagFinder(BagFinder("light red bags contain 1 bright white bag, 2 muted yellow bags."))
                .hasReadRule(rule)
    }


    @Test
    fun shouldBeAbleToReadMultipleRuleFromInput() {
        assertThatBagFinder(BagFinder(
                "light red bags contain 1 bright white bag, 2 muted yellow bags.\n" +
                        "dark orange bags contain 3 bright white bags, 4 muted yellow bags.")
        )
                .hasReadRule(rule)
                .hasReadRule(Rule("dark orange", "3 bright white", "4 muted yellow"))
    }

    @Test
    fun shouldFindBagsThatDirectlyContainsTheGivenColorBag() {
        val bagFinder1 = BagFinder(
                "light red bags contain 1 bright white bag, 2 muted yellow bags.\n" +
                        "dark orange bags contain 3 bright white bags, 4 muted green bags.")
        assertThat(
                bagFinder1
                        .findBagColorsThatCanContainsDirectly("muted green", bagFinder1.rules)
        )
                .hasSize(1)
                .containsOnly(
                        Rule("dark orange", "3 bright white", "4 muted green")
                )
        val bagFinder = BagFinder(
                "light red bags contain 1 bright white bag, 2 muted yellow bags.\n" +
                        "dark orange bags contain 3 bright white bags, 4 muted yellow bags.")
        assertThat(
                bagFinder
                        .findBagColorsThatCanContainsDirectly("muted yellow", bagFinder.rules)
        )
                .hasSize(2)
                .containsOnly(
                        Rule("light red", "1 bright white", "2 muted yellow"),
                        Rule("dark orange", "3 bright white", "4 muted yellow")
                )
    }

    @Test
    fun shouldFindAllColorBagThatCanIndirectlyFindTheGivenColorBag() {
        assertAll(
                {
                    assertThat(
                            BagFinder(
                                    "light red bags contain 1 bright white bag, 2 muted yellow bags.\n" +
                                            "dark orange bags contain 3 bright white bags, 4 muted green bags.")
                                    .findBagsThatCanContains("muted green")
                    )
                            .`as`("should find Bag color in first indentation ")
                            .hasSize(1)
                            .containsOnly(
                                    Rule("dark orange", "3 bright white", "4 muted green")
                            )
                    assertThat(
                            BagFinder(
                                    "light red bags contain 1 dark orange bag, 2 muted yellow bags.\n" +
                                            "dark orange bags contain 3 bright white bags, 4 muted green bags.")
                                    .findBagsThatCanContains("muted green")
                    )
                            .`as`("should find Bag color in second indentation ")
                            .hasSize(2)
                            .containsOnly(
                                    Rule("light red", "1 dark orange", "2 muted yellow"),
                                    Rule("dark orange", "3 bright white", "4 muted green")
                            )

                    assertThat(
                            BagFinder(
                                    "light red bags contain 1 dark orange bag, 2 muted yellow bags.\n" +
                                            "muted blue bags contain 1 light red bag, 2 muted red bags.\n" +
                                            "dark orange bags contain 3 bright white bags, 4 muted green bags.")
                                    .findBagsThatCanContains("bright white")
                    )
                            .`as`("should find Bag color in third indentation ")
                            .hasSize(3)
                            .containsOnly(
                                    Rule("light red", "1 dark orange", "2 muted yellow"),
                                    Rule("dark orange", "3 bright white", "4 muted green"),
                                    Rule("muted blue", "1 light red", "2 muted red")
                            )
                }
        )

    }


    @Test
    fun shouldDisplayNicely() {
        assertAll(
                {
                    assertThat(BagFinder()).hasToString("Bags Reads: None")
                    assertThat(BagFinder("light red bags contain 1 bright white bag, 2 muted yellow bags."))
                            .hasToString("Bags Reads: $rule")
                }
        )
    }
}


class BagFinderAssert(value: BagFinder) : AbstractObjectAssert<BagFinderAssert,
        BagFinder>(value, BagFinderAssert::class.java) {


    fun hasReadRule(rule: Rule): BagFinderAssert {
        if (!actual.rules.contains(rule)) {
            failWithMessage("%nExpecting <%s> to have read:<%s>%n", actual, rule)

        }
        return this
    }

    companion object {
        fun assertThatBagFinder(value: BagFinder): BagFinderAssert {
            return BagFinderAssert(value)
        }
    }
}