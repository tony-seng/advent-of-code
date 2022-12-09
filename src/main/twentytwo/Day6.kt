package main.twentytwo

import java.io.File

fun main() {
    val input = File("src/main/resources/twentytwo/day6.txt").readLines()

    val part1 = input.first().windowed(4).indexOfFirst { it.toSet().size == 4 } + 4
    println("Part 1 = $part1")

    val part2 = input.first().windowed(14).indexOfFirst { it.toSet().size == 14 } + 14
    println("Part 2 = $part2")
}