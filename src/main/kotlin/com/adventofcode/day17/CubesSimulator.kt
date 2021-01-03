package com.adventofcode.day17

data class Coordinates(val x: Int, val y: Int, val z: Int, val w: Int)


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


    fun simulateCyclesOn4thDim(nbCycles: Int): Long {

        var cubes = currentState.cubes.map { Coordinates(it.key.first, it.key.second, it.key.third, 0) to it.value }.toMap()
        repeat(nbCycles) {
            cubes += addAllNeighborsOn4thDim(cubes)
            cubes = changeStateAccordingToNeighborsSateOn4thDim(cubes)
        }

        return cubes.count { it.value.isActive() }.toLong()
    }

    private fun changeStateAccordingToNeighborsSateOn4thDim(cubes: Map<Coordinates, Cube>): Map<Coordinates, Cube> {
        return cubes
                .map {
                    val numberOfActiveNeighbors = neighborsOn4thDim(it.key).map { cubes[it] }.filterNotNull().count { it.isActive() }
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

    private fun addAllNeighborsOn4thDim(cubes: Map<Coordinates, Cube>): Map<Coordinates, Cube> {
        return cubes
                .map {
                    neighborsOn4thDim(it.key).map { currentCoordinates ->
                        currentCoordinates to (cubes[currentCoordinates] ?: Cube('.'))
                    }.distinct()
                }
                .flatten()
                .distinct()
                .toMap()
    }

    private fun neighborsOn4thDim(coordinates: Coordinates): Collection<Coordinates> {
        val neighborhood = -1..1
        return neighborhood.map { x ->
            neighborhood.map { y ->
                neighborhood.map { z ->
                    neighborhood.map { w ->
                        Coordinates(coordinates.x + x, coordinates.y + y, coordinates.z + z, coordinates.w + w)
                    }.filterNot { it == coordinates }
                }
            }
        }.flatten().flatten().flatten()
    }

}
