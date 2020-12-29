package com.adventofcode.day14


typealias  Memory = MutableMap<Long, Long>

class MaskProgram(private val mask: String, private vararg val valuesToWriteInMemory: Pair<Long, Long>) {
    private var memory = mutableMapOf<Long, Long>()

    val simplifiedMask = mask.toCharArray()
            .mapIndexed { index, it -> index to it }
            .filterNot { it.second == 'X' }
            .toMap()

    fun getMemory(): Memory {
        valuesToWriteInMemory.forEach {
            val valueTOWrite = longToString(it.second).toCharArray()
            simplifiedMask.forEach {
                valueTOWrite[it.key] = it.value
            }
            memory.put(it.first, stringToLong(valueTOWrite))
        }

        return memory
    }

    fun longToString(toConvert: Long): String {
        return toConvert.toString(2).padStart(36, '0')
    }

    fun stringToLong(toConvert: CharArray): Long {
        return toConvert.joinToString("").toLong(2)
    }

}
