package com.adventofcode.day15

typealias StartingNumber = Int
class MemoryGame(private val startingNumbers: List<StartingNumber>) {

    fun  spokeNumberAtTurn(nthTurn: Int): Int {

        val spokenNumbers = startingNumbers.mapIndexed {index,  it -> it to Pair(index+1,index+1) }.toMap().toMutableMap()

        if(nthTurn < startingNumbers.size +1) return startingNumbers[nthTurn-1]

        val firstNonStartingNumber=0
        if(nthTurn ==  startingNumbers.size +1) {
            return firstNonStartingNumber
        }

        updateSpokenNumber(spokenNumbers, firstNonStartingNumber, startingNumbers.size +1)

        var previousSpokenNumber=firstNonStartingNumber

        (startingNumbers.size +2).rangeTo(nthTurn)
                .forEach{thisTurn ->

                    val data= spokenNumbers[previousSpokenNumber]!!
                    val spokenNumber = data.second - data.first
                    updateSpokenNumber(spokenNumbers, spokenNumber, thisTurn)
                    previousSpokenNumber=spokenNumber
                }


        return previousSpokenNumber
    }

    private fun updateSpokenNumber(spokenNumbers: MutableMap<StartingNumber, Pair<Int, Int>>, spokenNumber: Int, thisTurn: Int) {
        if (spokenNumbers.containsKey(spokenNumber)) {
            spokenNumbers[spokenNumber] = spokenNumbers[spokenNumber]!!.copy(first = spokenNumbers[spokenNumber]!!.second, second = thisTurn)
        } else {
            spokenNumbers[spokenNumber] = Pair(thisTurn, thisTurn)
        }
    }

}
