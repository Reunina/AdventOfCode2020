package com.adventofcode.day14

class MaskPrograms(val input: Collection<String>) {


    fun getMemory(): Long {
        var memory = mutableMapOf<Long, Long>()
        val data = input.iterator()
        var blockData = arrayListOf<Pair<Long, Long>>()

        var line = ""
        var currentBitmask = ""

        while (data.hasNext()) {
            line = data.next()
            when (line.substring(0,3)) {
                "mem" -> blockData.add(
                        line.substringBefore(" =").substringAfter("[").dropLast(1).toLong() to
                                line.substringAfter("= ").toLong()
                )
                else ->
                    if (line.startsWith("mask = ")) {
                        if (blockData.size > 0) {
                            memory.putAll(MaskProgram(currentBitmask, *blockData.toTypedArray()).getMemory())
                            blockData = arrayListOf()
                        }
                        currentBitmask = line.split("= ").last()
                    }
            }
        }
        if (blockData.size > 0) {
            memory.putAll(MaskProgram(currentBitmask, *blockData.toTypedArray()).getMemory())
        }

        return memory.values.sum()

    }

}
