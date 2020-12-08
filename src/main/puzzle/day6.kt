package main.puzzle

import java.io.File

fun main() {
    val answersPerGroup: List<String> = File("src/main/resources/input/6.txt").readText().split("\n\n")
    println("Part1 = " + part1(answersPerGroup))
    println("Part2 = " + part2(answersPerGroup))
}

private fun part1(answersPerGroup: List<String>): Long =
        answersPerGroup.map {
            it.replace("\n", "").chars().distinct().count()
        }.sum()

private fun part2(answersPerGroup: List<String>): Int {
    return answersPerGroup.map {
        numAllYesInGroup(it)
    }.sum()
}

private fun numAllYesInGroup(group: String): Int =
        group.trim()
                .split('\n')
                .map(String::toSet)
                .reduceRight(Set<Char>::intersect).size