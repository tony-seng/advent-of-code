package main.puzzle

import java.io.File
import kotlin.math.max
import kotlin.math.min

fun main() {
    val inputs: List<String> = File("src/main/resources/input/5.txt").readLines()
    part1(inputs)
    part2(inputs)
}

private fun part1(inputs: List<String>) {
    val result = inputs.map {
        calculateRow(it.take(7)) * 8 + calculateColumn(it.takeLast(3))
    }.maxOrNull()
    println("Part 1 = $result")
}

private fun part2(inputs: List<String>) {
    var seatGrid : Array<Array<Int?>> = Array(128) { arrayOfNulls(8) }
    inputs.forEach {
        val i = calculateRow(it.take(7))
        val j = calculateColumn(it.takeLast(3))
        seatGrid[i][j] = 1
    }
    for (i in 0..127) {
        for(j in 0..7) {
            if(seatGrid[i][j] == null) {
                println("Row: $i and column: $j and seatId = ${i * 8 + j}")
            }
        }
    }
}

private fun calculateRow(rowCharacter: String) : Int {
    var start = 0
    var end = 128
    for(i in rowCharacter.indices) {
        when(rowCharacter[i]) {
            'F' -> end -= (end - start) / 2
            'B' -> start += (end - start) / 2
        }
    }
    return min(start, end)
}

private fun calculateColumn(columnCharacter: String) : Int {
    var start = 0
    var end = 8
    for(i in columnCharacter.indices) {
        when(columnCharacter[i]) {
            'L' -> end -= (end - start) / 2
            'R' -> start += (end - start) / 2
        }
    }
    return max(start, end) - 1
}