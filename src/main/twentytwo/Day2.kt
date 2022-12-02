package main.twentytwo

import java.io.File

fun main() {
    val records = File("src/main/resources/twentytwo/day2.txt").readLines()

    val part1 = records.sumOf {
    val (abc, xyz) = it.split(" ")
    val s1 = "XYZ".indexOf(xyz) + 1
    val s2 = when (abc to xyz) {
        "A" to "Z", "B" to "X", "C" to "Y" -> 0
        "A" to "X", "B" to "Y", "C" to "Z" -> 3
        "A" to "Y", "B" to "Z", "C" to "X" -> 6
        else -> 0
    }
        s1 +s2
    }

    println("Part 1 = $part1")

    val part2 = records.sumOf {
        val (abc, xyz) = it.split(" ")
        val b = when (abc to xyz) {
            "A" to "Y", "B" to "X", "C" to "Z" -> "A"
            "A" to "Z", "B" to "Y", "C" to "X" -> "B"
            "A" to "X", "B" to "Z", "C" to "Y" -> "C"
            else -> error("Value not possible")
        }
        val s1 = "ABC".indexOf(b) + 1
        val s2 = "XYZ".indexOf(xyz) * 3
        s1 +s2
    }

    println("Part 2 = $part2")
}