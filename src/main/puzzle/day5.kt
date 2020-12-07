package main.puzzle

import java.io.File
import kotlin.math.max
import kotlin.math.min

fun main() {
    val inputs: List<String> = File("src/main/resources/input/5.txt").readLines()
    val result = inputs.map {
        calculateRow(it.take(7)) * 8 + calculateColumn(it.takeLast(3))
    }.maxOrNull()
    println("Part 1 = $result")
}

fun calculateRow(rowCharacter : String) : Int {
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

fun calculateColumn(columnCharacter: String) : Int {
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