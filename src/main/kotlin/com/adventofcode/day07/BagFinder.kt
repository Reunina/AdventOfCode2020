package com.adventofcode.day07

class BagFinder(val rules: List<Rule>) {

    constructor(input: String) : this(input.split("\n").map(Rule.Companion::from))
    constructor() : this(emptyList())

    fun findNumberOfBagColorsThatCanContains(bagToContained: String): Int {
        return findBagColorsThatCanContains(bagToContained).count()
    }


    fun findBagColorsThatCanContains(bagNameToFind: String): List<Rule> {
        return findAllBagColorsThatCanContainsDirectlyOrNot(bagNameToFind, rules)

    }

    private fun findAllBagColorsThatCanContainsDirectlyOrNot(bagNameToFind: String, rules: List<Rule>): List<Rule> {
        val directRules = findBagColorsThatCanContainsDirectly(bagNameToFind, rules).distinct()
        val subRules = directRules.map { it.bagName }.map { findAllBagColorsThatCanContainsDirectlyOrNot(it, rules) }.flatten().distinct()
        if (subRules.isEmpty()) return directRules
        return directRules.union(subRules).distinct()
    }

    fun findBagColorsThatCanContainsDirectly(bagNameToFind: String, rules: List<Rule>): List<Rule> {
        return rules.filter { it.innerBags.containsKey(bagNameToFind) }
    }

    override fun toString(): String {
        return "Bags Reads: ${rules.joinToString { "$it" }.ifEmpty { "None" }}"
    }

    fun findAllNestedBagsThatAreIn(bagThatContains: String): Int {
        val rule = rules.firstOrNull { it.bagName == bagThatContains } ?: return 0
        val innerBags = rule.innerBags
        if (innerBags.isNullOrEmpty()) return 0
        val rawNumberOfBags = innerBags.map { it.value }.sum()
        val nestedBags = innerBags.map { it to it.key }.map { findAllNestedBagsThatAreIn(it.second) * it.first.value }.sum()
        return rawNumberOfBags + nestedBags
    }
}
