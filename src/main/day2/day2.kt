package main.day2

import java.io.File
import java.util.regex.Pattern

fun main() {
    part1()
    part2()
}

private fun part1() {
    var count = 0
    File("src/main/resources/input/2.txt").readLines().map { if(isCorrect(it)) count++ }
    println("Part1 = $count")
}

private fun isCorrect(passwordRule: String) : Boolean {
    val passwordRulePart = passwordRule.split(" ")
    val numbers = passwordRulePart[0].split("-")
    if(checkPasswordRule(numbers[0].toInt(), numbers[1].toInt(), passwordRulePart[1][0], passwordRulePart[2])) {
        return true
    }
    return false
}

private fun checkPasswordRule(number1: Int, number2: Int, char: Char, password: String) : Boolean {
    val countChar = countOccurrences(password, char)
    return countChar in number1..number2
}

private fun countOccurrences(s: String, ch: Char): Int {
    val matcher = Pattern.compile(ch.toString()).matcher(s)
    var counter = 0
    while (matcher.find()) {
        counter++
    }
    return counter
}

private fun part2() {
    var valid = 0
    File("src/main/resources/input/2.txt").readLines().map {
        var parts = it.split(" ")
        val numbers = parts[0].split("-")
        var charCount = 0
        if(parts[2].length > numbers[1].toInt()-1) {
            if (parts[2][numbers[0].toInt()-1] == parts[1][0]) {
                charCount++;
            }
            if (parts[2][numbers[1].toInt()-1] == parts[1][0]){
                charCount++;
            }
        }
        if (charCount == 1) {
            valid++;
        }
    }
    println("Part1 = $valid")
}

