package com.adventofcode.day10

typealias  Adapters = List<Adapter>
typealias Adapter = Int

enum class JoltDifferences(private val jolts: Int) {
    ONE_JOLT_DIFF(1), THREE_JOLT_DIFF(3);

    companion object {
        fun forDiff(diff: Int): JoltDifferences {
            return JoltDifferences.values().first { it.jolts == diff }
        }
    }
}

class HomeMadeAdapter(private val adaptersJolts: Adapters) {

    private val startingJolt = 0
    val rateJolts = adaptersJolts.max()!! + 3

    constructor(adaptersAsString: String) : this(adaptersAsString.split('\n').map { it.toInt() }.sorted())

    fun joinAdapters(): Map<JoltDifferences?, Int> {

        val availableAdapters: MutableMap<Adapter, JoltDifferences?> = adaptersJolts.map { it to null }.toMap().toMutableMap()
        var joltage = startingJolt
        while (availableAdapters.any { it.value == null }) {
            val adapterToUse = findMinimalAdapterToPlugWith(joltage) ?: rateJolts
            availableAdapters[adapterToUse] = JoltDifferences.forDiff(adapterToUse - joltage)
            joltage = adapterToUse
        }
        return availableAdapters.map { it.value }.groupBy { it }.map { it.key to it.value.size }.toMap()
    }

    private fun findMinimalAdapterToPlugWith(joltage: Int): Adapter? {
        val adaptersToUse = findAdaptersToPlugWith(joltage, adaptersJolts)
        return adaptersToUse.min()
    }

    private fun findAdaptersToPlugWith(joltage: Int, adapters: Adapters): Adapters {
        val authorizedJolts = 1.rangeTo(3).map { joltage + it }.toList()
        return adapters.filter { it in authorizedJolts }
    }

    fun findAllValidArrangements(): Long {
        val allAdapters = adaptersJolts.map { it.toLong() }.sorted()
        val validPaths = mutableMapOf(0L to 1L)
        allAdapters.forEach {
            adapterIndex ->
            validPaths[adapterIndex] = (1..3).map { previousAdapterIndex -> validPaths.getOrDefault(adapterIndex-previousAdapterIndex, 0) }.sum()
        }
        return validPaths.getValue(allAdapters.last())
    }



}

