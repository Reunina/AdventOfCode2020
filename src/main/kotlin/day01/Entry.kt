package day01

import java.io.File

class EntryCode(val firstEntry: Int, val secondEntry: Int) {

    private val code: Int = firstEntry * secondEntry


    constructor() : this(0, 0)

    private fun findEntriesThatSumUpTo2020(input: List<Int>): Set<Int> {
        return input.associateWith { 2020 - it }.filterValues { input.contains(it) }.keys
    }

    fun findFrom(input: List<Int>): EntryCode {
        val entries = findEntriesThatSumUpTo2020(input)
        return EntryCode(entries.first(), entries.last())
    }

    override fun toString(): String {
        return "$firstEntry + $secondEntry = $code"
    }


}


fun main(args: Array<String>) {
    val input: List<Int> = File("src/main/resources/day01/puzzle_input_for_entry.txt").readLines().map(String::toInt)
    println("Code to enter Advent Of Code 2020: " + EntryCode().findFrom(input))
}
