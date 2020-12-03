package day1

import common.readFileAsLinesUsingReadLines

fun main() {
    val entries : List<Int> = readFileAsLinesUsingReadLines("./src/day1/input.txt").map { it -> Integer.valueOf(it) }.toList()

    for(index in entries.indices) {
        for(i in entries.size-1 downTo 0) {
            if(entries[index] + entries[i] == 2020) {
                println((entries[index] * entries[i]))
                return;
            }
        }
    }
}
