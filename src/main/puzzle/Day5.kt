package main.puzzle

import java.io.File
import kotlin.math.max
import kotlin.math.min

fun main() {
    val inputs: List<String> = File("src/main/resources/input/5.txt").readLines()
    println("Part1 = ${findHighestSeatId(inputs)}")
    println("Part2 = ${findSeat(inputs)}")
}

private const val ROW_CHAR_NB = 7
private const val COLUMN_CHAR_NB = 3
private const val ROW_MULTIPLIER = 8
private const val NUMBER_OF_ROW = 128
private const val NUMBER_OF_COLUMN = 8

private fun findHighestSeatId(inputs: List<String>) = inputs.map {
        calculateSeatId(calculateRow(it.take(ROW_CHAR_NB)), calculateColumn(it.takeLast(COLUMN_CHAR_NB)))
    }.maxOrNull()


private fun findSeat(inputs: List<String>) : Int {
    val seatGrid : Array<Array<Int?>> = Array(NUMBER_OF_ROW) { arrayOfNulls(NUMBER_OF_COLUMN) }
    inputs.forEach {
        val row = calculateRow(it.take(ROW_CHAR_NB))
        val column = calculateColumn(it.takeLast(COLUMN_CHAR_NB))
        seatGrid[row][column] = 1
    }
    for (i in seatGrid.indices) {
        if(seatGrid[i].any { it == 1 }) {
            for (j in seatGrid[i].indices) {
                if (seatGrid[i][j] == null && i>0 && j>0 && seatGrid[i-1][j-1] != null) {
                    return calculateSeatId(i, j)
                }
            }
        }
    }
    return 0
}

private fun calculateRow(rowCharacter: String) : Int {
    var start = 0
    var end = NUMBER_OF_ROW
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
    var end = NUMBER_OF_COLUMN
    for(i in columnCharacter.indices) {
        when(columnCharacter[i]) {
            'L' -> end -= (end - start) / 2
            'R' -> start += (end - start) / 2
        }
    }
    return max(start, end) - 1
}

private fun calculateSeatId(row: Int, column: Int) = row * ROW_MULTIPLIER + column