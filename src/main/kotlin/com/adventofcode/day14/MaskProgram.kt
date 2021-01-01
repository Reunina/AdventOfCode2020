package com.adventofcode.day14


typealias  Memory = MutableMap<Long, Long>

class MaskProgram(private val mask: String, private vararg val valuesToWriteInMemory: Pair<Long, Long>) {
    private var memory = mutableMapOf<Long, Long>()

    private val simplifiedMask = mask.toCharArray()
            .mapIndexed { index, it -> index to it }
            .filterNot { it.second == 'X' }
            .toMap()

    private val simplifiedMaskForV2 = mask.toCharArray()
            .mapIndexed { index, it -> index to it }
            .filterNot { it.second == '0' }
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

    fun getMemoryV2(): MutableMap<Long, Long> {
        valuesToWriteInMemory.forEach {
            val memoryAdresse = it.first
            val valueToWrite = it.second
            val memoryAdresseAsString = longToString(memoryAdresse).toCharArray()
            simplifiedMaskForV2
                    .filter { it.value == '1' }
                    .forEach { memoryAdresseAsString[it.key] = '1' }

            getMemoryAdresses(memoryAdresse).forEach {
                memory[it] = valueToWrite
            }
        }
        return memory
    }

    fun getMemoryAdresses(memoryAddress: Long): List<Long> {
            val memoryAdresseAsString = longToString(memoryAddress).toCharArray()
            simplifiedMaskForV2
                    .filter { it.value == '1' }
                    .forEach { memoryAdresseAsString[it.key] = '1' }

            val XsToReplace = simplifiedMaskForV2
                    .filter { it.value == 'X' }
                    .keys
                    .sorted()
            var values = listOf(memoryAdresseAsString)
            XsToReplace
                    .forEach {index ->
                        values
                                .forEach {
                                    value ->
                                    val temp0 = value.clone()
                                    val temp1 = value.clone()
                                    temp0[index] = '0'
                                    temp1[index] = '1'
                                    if(! values.contains(temp0)) {
                                        values=values.plus(temp0).distinct()
                                    }
                                    if(! values.contains(temp1)) {
                                        values=values.plus(temp1).distinct()
                                    }
                                }
                    }


        return values.distinct().map { stringToLong(it) }.sorted()
    }
}
