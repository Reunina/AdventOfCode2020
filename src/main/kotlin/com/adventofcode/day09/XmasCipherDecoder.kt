package com.adventofcode.day09

typealias Code = Long

class XmasCipherDecoder(val preambleAndCodes: Collection<Code>, val preambleSize : Int) {

    var preamble = preambleAndCodes.toMutableList().subList(0, preambleSize)
    val codes = preambleAndCodes.toList().subList( preambleSize ,preambleAndCodes.size )

    constructor( preamble: Collection<Code>,   codes: Collection<Code> = mutableListOf()) :
            this(preamble + codes , preamble.size)


    constructor(preambleAsInts: List<Int>) : this(preambleAsInts.map { it.toLong() }.toList())

    companion object {

        fun readFrom(input: String, preambleLength: Int): XmasCipherDecoder {
            val data = input.split('\n').filterNot{it.isEmpty()}.map { it.toLong() }
            val preamble = data.subList(0, preambleLength)
            val codes = data.subList(preambleLength , data.size)
            return XmasCipherDecoder(preamble, codes)
        }

    }

    fun validate(expected: Int): Boolean {
        return validate (expected.toLong())
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
        codes.forEach {
            val validity = validate(it)
            if (!validity) return it
            if (validity) read(it)
        }
        return null
    }

    fun findEncryptionWeakness(): Code {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
