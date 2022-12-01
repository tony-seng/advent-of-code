package main.twenty

import java.io.File

fun main() {
    val passwordRules = File("src/main/resources/twenty/2.txt").readLines()
    println("Part1 = ${countValidPasswords(passwordRules)}")
    println("Part2 = ${countValidPasswords2(passwordRules)}")
}

private fun countValidPasswords(passwordRules: List<String>) : Int =
        passwordRules.filter { passwordRule ->
            isCorrect(passwordRule)
        }.count()


private fun isCorrect(passwordRule: String) : Boolean {
    val passwordRulePart = passwordRule.split(" ")
    val numbers = passwordRulePart[0].split("-")
    return checkPasswordRule(numbers[0].toInt()..numbers[1].toInt(),
            passwordRulePart[1][0],
            passwordRulePart[2])
}

private fun checkPasswordRule(range: IntRange, charToCount: Char, password: String) : Boolean {
    val countChar = password.filter { char ->
        char == charToCount
    }.count()
    return countChar in range
}

private fun countValidPasswords2(passwordRules: List<String>) : Int {
    var validCount = 0
    passwordRules.map { rule ->
        val parts = rule.split(" ")
        val numbers = parts[0].split("-")
        val firstIndexToCheck = numbers[0].toInt() - 1
        val secondIndexToCheck = numbers[1].toInt() - 1
        val charToChek = parts[1][0]
        var charCount = 0
        val password = parts[2]
        if(password.length > secondIndexToCheck) {
            if (password[firstIndexToCheck] == charToChek) {
                charCount++;
            }
            if (password[secondIndexToCheck] == charToChek) {
                charCount++;
            }
        }
        if (charCount == 1) {
            validCount++;
        }
    }
    return validCount
}


