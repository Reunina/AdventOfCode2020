package com.adventofcode.day07

class BagFinder(val rules: List<Rule>) {

    constructor(input: String) : this(input.split("\n").map(Rule.Companion::from))
    constructor() : this(emptyList())

    fun findNumberOfBagsThatCanContains(bagToContained: String): Int {
        return findBagsThatCanContains(bagToContained).count()
    }


    fun findBagsThatCanContains(bagNameToFind: String): List<Rule> {
        return findRulesAndSubRulesFor(bagNameToFind, rules)

    }

    private fun findRulesAndSubRulesFor(bagNameToFind: String, rules: List<Rule>): List<Rule>{
        val directRules = findBagColorsThatCanContainsDirectly(bagNameToFind, rules).distinct()
        val subRules = directRules.map { it.bagName }.map { findRulesAndSubRulesFor(it, rules) }.flatten().distinct()
        if (subRules.isEmpty()) return directRules
        return directRules.union(subRules).distinct()
    }

    fun findBagColorsThatCanContainsDirectly(bagNameToFind: String, rules: List<Rule>): List<Rule> {
        return rules.filter { it.innerBags.containsKey(bagNameToFind)}
    }

    override fun toString(): String {
        return "Bags Reads: ${rules.joinToString { "$it" }.ifEmpty { "None" }}"
    }

}
