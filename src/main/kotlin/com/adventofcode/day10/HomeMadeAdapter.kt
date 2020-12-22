package com.adventofcode.day10

typealias  Adapters = Collection<Adapter>
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

    var differences: Map<JoltDifferences, Int> = mutableMapOf()

    constructor(adaptersAsString: String) : this(adaptersAsString.split('\n').map { it.toInt() }.sorted())


    fun rateJolts(): Adapter {
        return adaptersJolts.max()!! + 3
    }


    fun joinAdapters(): HomeMadeAdapter {
        val availableAdapters = adaptersJolts.toMutableList()
        var joltage = startingJolt

        while (availableAdapters.isNotEmpty()) {
            val adapterToUse = findAdapterToPlugWithAmong(joltage, adaptersJolts)
            availableAdapters.remove(adapterToUse)
            joltage = adapterToUse
        }
        findAdapterToPlugWithAmong(joltage, listOf(rateJolts()))
        return this
    }

    private fun classifyTheDifference(initialJoltage: Int, newJolatge: Adapter) {
        val diff = newJolatge - initialJoltage
        val typeOfDiff = JoltDifferences.forDiff(diff)
        differences += Pair(typeOfDiff, differences.getOrDefault(typeOfDiff, 0) + 1)
    }

    private fun findAdapterToPlugWithAmong(joltage: Int, availableAdapters: Adapters): Adapter {
        val authorizedJolts = 1.rangeTo(3).map { joltage + it }.toList()
         val adapterToUse = availableAdapters.filter { it in authorizedJolts }.min()!!

        classifyTheDifference(joltage, adapterToUse)
        return adapterToUse
    }
}

