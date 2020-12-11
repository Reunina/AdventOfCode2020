package day01

import java.io.File

class EntryCode(val firstEntry: Int, val secondEntry: Int, val thirdEntry: Int) {

    private val code: Int = firstEntry * secondEntry * thirdEntry


    constructor() : this(0, 0,0)
    constructor(firstEntry: Int, secondEntry: Int) : this(firstEntry,secondEntry,1)

    private val EXPECTED_SUM = 2020

    private fun findTwoEntriesThatSumUpTo2020(input: List<Int>): Set<Int> {
        return input.associateWith { EXPECTED_SUM - it }.filterValues { input.contains(it) }.keys
    }

    private fun findThreeEntriesThatSumUpTo2020(input: List<Int>): Set<Int> {
        val values = input
                .map { it to findTwoOtherEntriesThatSumUpTo2020With(it, input) }
                .toMap()
                .filterNot { it.value.isNullOrEmpty() }
                .entries
                .first()
                .toPair()
        return setOf(values.first) union values.second
    }

    private fun findTwoOtherEntriesThatSumUpTo2020With(firstEntry: Int, input: List<Int>): Set<Int> {
        return input.associateWith { EXPECTED_SUM - (firstEntry + it)  }
                .filterValues { input.contains(it) }
                .keys
    }

    fun findFromTwoEntriesOf(input: List<Int>): EntryCode {
        val entries = findTwoEntriesThatSumUpTo2020(input)
        return EntryCode(entries.first(), entries.last())
    }

    fun findFromThreeEntriesOf(input: List<Int>): EntryCode {
        val entries = findThreeEntriesThatSumUpTo2020(input)
        return EntryCode(entries.first(), entries.elementAt(1), entries.last())
    }

    override fun toString(): String {
        return "$firstEntry + $secondEntry = $code"
    }
}


fun main(args: Array<String>) {
    val input: List<Int> = File("src/main/resources/day01/puzzle_input_for_entry.txt").readLines().map(String::toInt)
    println("Code to enter Advent Of Code 2020:       " + EntryCode().findFromTwoEntriesOf(input))
    println("Code to continue on Advent Of Code 2020: " + EntryCode().findFromThreeEntriesOf(input))
}
