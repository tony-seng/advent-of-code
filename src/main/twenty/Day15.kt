package main.twenty

fun main() {
    println("Part1 = ${solve(2020)}")
    println("Part2 = ${solve(30_000_000)}")
}

private val startingNumbers = "7,12,1,0,16,2".split(",").map { it.toInt() }

private fun memoryGame(startingNumbers: List<Int>): Sequence<Int> = sequence {
    yieldAll(startingNumbers)
    val memory = startingNumbers.mapIndexed { index, i -> i to index }.toMap().toMutableMap()
    var turns = startingNumbers.size
    var sayNext = 0
    while(true) {
        yield(sayNext)
        val lastTimeSpoken = memory[sayNext] ?: turns
        memory[sayNext] = turns
        sayNext = turns - lastTimeSpoken
        turns++
    }
}

fun solve(turns: Int): Int =
        memoryGame(startingNumbers).drop(turns-1).first()

