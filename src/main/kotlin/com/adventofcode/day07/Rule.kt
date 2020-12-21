package com.adventofcode.day07

typealias Bags = Map<Bag, Int>
typealias Bag = String

class Rule(val bagName: Bag) {

    var innerBags: Bags = mutableMapOf()

    constructor(bagName: Bag, vararg innerBagsAsString: Bag) : this(bagName) {
        innerBags = innerBagsAsString
                .map { it.split(" ") }
                .filterNot { it.isNullOrEmpty() }
                .filterNot { it.size == 1 }
                .map { it.subList(1, it.size).joinToString(" ", "", "") to it[0].toInt() }
                .toMap()
    }

    override fun toString(): Bag {
        if (innerBags.isNullOrEmpty()) return Bag.format("[%s] contains no other bag", bagName)
        return Bag.format("[%s] contains %s", bagName, innerBags)
    }

    companion object {
        fun from(input: Bag): Rule {
            val values = input
                    .split(" bags contain | bag(s){0,1}, | bag(s){0,1}\\.".toRegex())
            if(values.isNotEmpty() && values[1] != "no other") return  Rule(values[0], *values.subList(1, values.size).toTypedArray())
            return Rule(values[0])
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Rule) return false
        return bagName == other.bagName && innerBags == other.innerBags
    }

}
