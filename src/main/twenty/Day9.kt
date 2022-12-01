package main.twenty

import java.io.File

fun main() {
    val inputs = File("src/main/resources/twenty/9.txt").readLines().
            map(String::toLong)

    println("Part 1 = ${part1(inputs)}")
    println("Part 2 = ${part2(inputs)}")
}

private const val PREAMBLE = 25

private fun part1(data : List<Long>): Long {
    for (index in PREAMBLE until data.size) {
        if (!findPairs(data.subList(index - PREAMBLE, index), data[index]))
            return data[index]
    }

    throw RuntimeException("Unable to find result!")
}

private fun findPairs(data: List<Long>, target: Long): Boolean {
    return data.mapNotNull { first ->
        data.find { second ->
            first + second == target && first != second
        }
    }.isNotEmpty()
}

private fun part2(data: List<Long>): Long {
    val target = part1(data)

    for (index in data.indices) {
        for (reversed in data.size - 1 downTo index + 1) {
            val sublist = data.subList(index, reversed + 1).toSortedSet()
            if (sublist.sum() == target)
                return sublist.first() + sublist.last()
        }
    }

    throw RuntimeException("Unable to find result!")
}
