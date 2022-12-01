package main.twentytwo


import java.io.File

fun main() {
    val calories = File("src/main/resources/twentytwo/day1.txt").readLines().joinToString("-")
            .split("--")
            .map { elf ->
                elf
                        .split("-")
                        .map(String::toLong)
                        .sum()
            }
            .sorted()

    fun part1() = calories.takeLast(1)

    fun part2() = calories.takeLast(3).sum()

    println("Part 1 = ${part1()}")
    println("Part 1 = ${part2()}")
}