package main.day1

import java.io.File

fun main() {
    val entries : List<Int> = File("src/main/resources/input/1.txt").readLines().map { Integer.valueOf(it) }.toList()

    println("Part1 = " + productOf2(entries, 2020))
    println("Part2 = " + productOf3(entries))

}

private fun productOf2(input: List<Int>, target: Int): Int {
    input.forEach {
        val difference = target - it
        if (difference > 0 && input.contains(difference)) {
            return it * difference
        }
    }
    return 0
}

private fun productOf3(input: List<Int>): Int {
    input.forEach {
        val productOf2 = productOf2(input, 2020 - it)
        if (productOf2 > 0) {
            return productOf2 * it
        }
    }

    return 0
}
