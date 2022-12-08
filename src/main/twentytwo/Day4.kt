package main.twentytwo

import java.io.File

fun main() {
    val input = File("src/main/resources/twentytwo/day4.txt").readLines().asSequence()

    val part1 = parseInput(input).count { (a, b) ->
        a.contains(b) || b.contains(a)
    }
    println("Part 1 = $part1")

    val part2 = parseInput(input).count { (a, b) ->
        a.overlaps(b) || b.overlaps(a)
    }
    println("Part 2 = $part2")
}

private fun parseInput(input: Sequence<String>) = input.map {
    line ->
    val (a, b) = line.split(",")
    val (aMin, aMax) = a.split("-").map { it.toInt() }
    val (bMin, bMax) = b.split("-").map { it.toInt() }
    (aMin..aMax) to (bMin..bMax)
}

private fun IntRange.contains(other: IntRange) = other.first >= first && other.last <= last

private fun IntRange.overlaps(other: IntRange) = first in other || last in other
