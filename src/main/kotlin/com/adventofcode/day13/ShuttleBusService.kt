package com.adventofcode.day13

class ShuttleBusService(private val actualTimeStamp: Long, private vararg val busIds: Int) {


    companion object {
        fun readFrom(actualTimeStamp: String, busIds : String): ShuttleBusService {

            return ShuttleBusService(actualTimeStamp.toLong(), *busIds.split(',').filterNot { it =="x" }.map { it.toInt() }.toIntArray() )
        }
    }

    fun findEarliestDeparture(): Pair<Int, Long>? {
        return busIds.map { it to foundNextTimeStamp(it) }.minBy { it.second }
    }

    private fun foundNextTimeStamp(it: Int) = actualTimeStamp - actualTimeStamp.rem(it) + it

    fun  findEarliestIdByWaitingTime(): Long {
        val earliestDeparture = findEarliestDeparture()
        return earliestDeparture!!.first.times(  earliestDeparture.second- actualTimeStamp)
    }

}
