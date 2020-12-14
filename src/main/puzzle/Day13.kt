package main.puzzle

import java.io.File

private val inputs = File("src/main/resources/input/13.txt").readLines()
private val startTime: Int = inputs.first().toInt()
private val busses: List<Int> = inputs
        .last()
        .split(",")
        .mapNotNull { s -> if (s == "x") null else s.toInt() }

fun main() {
    println("Part1 = ${part1()}")
    println("Part2 = ${part2()}")
}

private fun part1() : Int =
        generateSequence(startTime) { it + 1 }
                .mapNotNull { time ->
                    busses.firstOrNull { bus -> time % bus == 0 }
                            ?.let { bus -> Pair(time, bus) }
                }
                .first()
                .run { (first - startTime) * second }

data class IndexedBus(val index: Int, val bus: Long)

private val indexedBusses: List<IndexedBus> = inputs
        .last()
        .split(",")
        .mapIndexedNotNull { index, s -> if (s == "x") null else IndexedBus(index, s.toLong()) }

private fun part2(): Long {
    var stepSize = indexedBusses.first().bus
    var time = 0L
    indexedBusses.drop(1).forEach { (offset, bus) ->
        while ((time + offset) % bus != 0L) {
            time += stepSize
        }
        stepSize *= bus // New Ratio!
    }
    return time
}