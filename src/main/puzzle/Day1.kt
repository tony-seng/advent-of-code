package main.puzzle

import java.io.File

fun main() {
    val expenses : List<Int> = File("src/main/resources/input/1.txt").readLines()
            .map(String::toInt)
            .toList()

    println("Part1 = " + productOf2(expenses, 2020))
    println("Part2 = " + productOf3(expenses))

}

private fun productOf2(expenses: List<Int>, target: Int): Int {
    expenses.forEach { expense ->
        val difference = target - expense
        if (difference > 0 && expenses.contains(difference)) {
            return expense * difference
        }
    }
    return 0
}

private fun productOf3(expenses: List<Int>): Int {
    expenses.forEach { expense ->
        val productOf2 = productOf2(expenses, 2020 - expense)
        if (productOf2 > 0) {
            return productOf2 * expense
        }
    }
    return 0
}
