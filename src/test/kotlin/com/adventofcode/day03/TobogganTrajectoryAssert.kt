package com.adventofcode.day03

import org.assertj.core.api.AbstractObjectAssert

class TobogganTrajectoryAssert(value: TobogganTrajectory) : AbstractObjectAssert<TobogganTrajectoryAssert, TobogganTrajectory>(value, TobogganTrajectoryAssert::class.java) {


    fun hasTreesEncounteredAtNumberOf(expectedNumberOfEncounteredTrees: Int): TobogganTrajectoryAssert {
        if (actual.treesEncountered() != expectedNumberOfEncounteredTrees) {
            failWithMessage("Actual value <%s> has not the expected number of trees encountered (%s)", actual, expectedNumberOfEncounteredTrees)
        }
        return this
    }

    companion object {
        fun assertThatTobogganTrajectory(value: TobogganTrajectory): TobogganTrajectoryAssert {
            return TobogganTrajectoryAssert(value)
        }
    }


}