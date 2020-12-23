package com.adventofcode.day09

import java.util.*

typealias Code = Long

class XmasCipherDecoder(private val preambleAndCodes: Collection<Code>, private val preambleSize: Int) {

    private val initialPreamble = preambleAndCodes.toMutableList().subList(0, preambleSize)
    private val codes = preambleAndCodes.toList().subList(preambleSize, preambleAndCodes.size)

    var preamble = initialPreamble

    constructor(preamble: Collection<Code>, codes: Collection<Code> = mutableListOf()) :
            this(preamble + codes, preamble.size)


    constructor(preambleAsInts: List<Int>) : this(preambleAsInts.map { it.toLong() }.toList())

    companion object {

        fun readFrom(input: String, preambleLength: Int): XmasCipherDecoder {
            val data = input.split('\n').filterNot { it.isEmpty() }.map { it.toLong() }
            val preamble = data.subList(0, preambleLength)
            val codes = data.subList(preambleLength, data.size)
            return XmasCipherDecoder(preamble, codes)
        }
    }

    fun validate(expected: Int): Boolean {
        return validate(expected.toLong())
    }


    private fun validate(expected: Code): Boolean {
        return preamble
                .filterNot { it * 2 == expected }
                .any { (expected - it in preamble) or (it - expected in preamble) }
    }

    fun read(code: Code) {
        preamble = (preamble - listOf(preamble.first()) + listOf(code)).toMutableList()
    }

    fun findFirstInvalidCode(): Code? {
        preamble = initialPreamble
        codes.forEach {
            val validity = validate(it)
            if (!validity) return it
            if (validity) read(it)
        }
        return null
    }

    fun findEncryptionWeakness(): Long {
        val invalidCode = findFirstInvalidCode()

        val values = preambleAndCodes
                .map { generateAllSequencesForCode(it) }
                .first { it.containsKey(invalidCode) }[invalidCode]

        return values!!.first!! + values.second!!.toLong()
    }

    private fun generateAllSequencesForCode(departureCode: Code): Map<Long, Pair<Code?, Code?>> {
        val maxsize = preambleAndCodes.size
        val fromIndex = preambleAndCodes.indexOf(departureCode)
        return (preambleAndCodes as ArrayList).subList(fromIndex, maxsize)
                .mapIndexed { index, _ -> preambleAndCodes.subList(fromIndex, maxsize - index) }
                .filter { it.size > 1 }
                .map { it.sum() to Pair(it.min(), it.max()) }
                .toMap()
    }


}
