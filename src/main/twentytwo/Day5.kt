package main.twentytwo

import java.io.File

fun main() {
    val input = File("src/main/resources/twentytwo/day5.txt").readLines()

    val crates = (1..9).associateWith { emptyList<Char>() }.toMutableMap()
    input.filter { '[' in it }.forEach { line ->
            for (stackIndex in 1..9) {
                val lineIndex = 1 + (stackIndex - 1) * 4
                if (line.lastIndex < lineIndex || line[lineIndex] == ' ') continue
                crates[stackIndex] = listOf(line[lineIndex]) + crates.getValue(stackIndex)
        }
    }
    input.filter { "move" in it }.forEach { line ->
        val (amount, from, to) = "\\d+".toRegex().findAll(line).map { it.value.toInt() }.toList()
        //repeat(amount) {
            crates[to] = crates.getValue(to) + crates.getValue(from).takeLast(amount) //.last for part 1
            crates[from] = crates.getValue(from).dropLast(amount) //.dropLast(1) for part 1
        //}
    }

    println("Part 1 = ${crates.values.map { it.last() }.joinToString("")}")

}