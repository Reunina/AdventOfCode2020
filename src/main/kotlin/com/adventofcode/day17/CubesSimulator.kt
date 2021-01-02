package com.adventofcode.day17

class CubesSimulator(private val initialState: List<String>) {

    private var currentState = State.fromInput(*initialState.toTypedArray())


    fun simulateCycles(nbCycles: Int): Long {

        var cubes = currentState.cubes
        repeat(nbCycles) {
            cubes += addAllNeighbors(cubes)
            cubes = changeStateAccordingToNeighborsSate(cubes)
        }

        return cubes.count { it.value.isActive() }.toLong()
    }

    private fun changeStateAccordingToNeighborsSate(cubes: Map<Triple<Int, Int, Int>, Cube>): Map<Triple<Int, Int, Int>, Cube> {
        return cubes
                .map {
                    val numberOfActiveNeighbors = neighbors(it.key).map { cubes[it] }.filterNotNull().count { it.isActive() }
                    var currentCube = cubes[it.key]!!
                    when (currentCube.isActive()) {
                        true -> if (numberOfActiveNeighbors != 2 && numberOfActiveNeighbors != 3) {
                            currentCube = Cube('.')
                        }
                        false -> if (numberOfActiveNeighbors == 3) {
                            currentCube = Cube('#')
                        }
                    }
                    it.key to currentCube
                }.toMap()
    }

    private fun addAllNeighbors(cubes: Map<Triple<Int, Int, Int>, Cube>): Map<Triple<Int, Int, Int>, Cube> {
        return cubes
                .map {
                    neighbors(it.key).map { currentCoordinates ->
                        currentCoordinates to (cubes[currentCoordinates] ?: Cube('.'))
                    }.distinct()
                }
                .flatten()
                .distinct()
                .toMap()
    }

    private fun neighbors(coordinates: Triple<Int, Int, Int>): Collection<Triple<Int, Int, Int>> {
        val neighborhood = -1..1
        return neighborhood.map { x ->
            neighborhood.map { y ->
                neighborhood.map { z ->
                    Triple(coordinates.first + x, coordinates.second + y, coordinates.third + z)
                }.filterNot { it == coordinates }
            }
        }.flatten().flatten()
    }

}
