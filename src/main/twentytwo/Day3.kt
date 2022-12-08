package main.twentytwo

import java.io.File

fun main() {
    val lines = File("src/main/resources/twentytwo/day3.txt").readLines()

    val part1 = lines.asSequence()
            .map { it.take(it.length / 2).toSet() to it.takeLast(it.length / 2).toSet() }
            .map { (a,b) -> a.single { it in b } }
            .sumOf(::priority)

    println("Part 1= $part1")

    val part2 = lines.asSequence()
            .chunked(3)
            .map { Triple(it[0].toSet(), it[1].toSet(), it[2].toSet() ) }
            .map { (a,b,c) -> a.filter { it in b }.single { it in c } }
            .sumOf(::priority)

    println("Part 2= $part2")
}

private fun priority(c: Char) = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(c) + 1