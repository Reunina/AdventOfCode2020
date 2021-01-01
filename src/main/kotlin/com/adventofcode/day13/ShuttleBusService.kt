package com.adventofcode.day13


class ShuttleBusService(private val actualTimeStamp: Long, private vararg val busIds: Int) {


    companion object {
        fun readFrom(actualTimeStamp: String, busIds: String): ShuttleBusService {
            return ShuttleBusService(actualTimeStamp.toLong(), *busIds.split(',').filterNot { it == "x" }.map { it.toInt() }.toIntArray())
        }
    }

    fun findEarliestDeparture(): Pair<Int, Long>? {
        return busIds.map { it to foundNextTimeStamp(it) }.minBy { it.second }
    }

    private fun foundNextTimeStamp(it: Int) = actualTimeStamp - actualTimeStamp.rem(it) + it

    fun findEarliestIdByWaitingTime(): Long {
        val earliestDeparture = findEarliestDeparture()
        return earliestDeparture!!.first.times(earliestDeparture.second - actualTimeStamp)
    }
}

class ShuttleBusServicePart02(vararg val busIds: String) {

    fun findEarliestTimeStampThatMatchesWithTheBusDeparturePosition(): Long {

        val busIdWithIndexReduces = busIds
                .mapIndexed { index, id -> id to index.toLong() }
                .filterNot { it.first == "x" }
                .map { it.first.toLong() to it.second }
                .sortedBy { it.second }
                .toMap()
                .toMutableMap()

        var minimalBusId = busIdWithIndexReduces.minBy { it.value }!!.key
        busIdWithIndexReduces.remove(minimalBusId)

        var timestamp = 0L
        busIdWithIndexReduces.forEach { (busId, busIndex) ->
            while ((timestamp + busIndex) % busId != 0L) {
                timestamp += minimalBusId
            }
            minimalBusId = minimalBusId.times(busId)
        }
        return timestamp

    }
}