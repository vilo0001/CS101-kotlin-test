package com.example.cs101_test.exercises

object Part2ConditionalLogic {
    // ---------------------- EXERCISE 1
    // Create a function that takes an integer as a parameter and returns "Positive", "Negative", or "Zero".
    fun checkNumber(number: Int): String {
        // Your code here
        return ""
    }

    // ---------------------- EXERCISE 2
    // Create a function that takes a string and prints each character on a new line.
    fun printChars(input: String) {
        // Your code here
    }

    // ---------------------- EXERCISE 3
    // Create a function that takes an integer and returns the sum of its digits.
    fun sumDigits(number: Int): Int {
        // Your code here
        return 0
    }

    // ---------------------- EXERCISE 4
    // Create a function that takes a list of integers and returns a new list with only the odd numbers.
    fun filterOddNumbers(numbers: List<Int>): List<Int> {
        // Your code here
        return listOf()
    }

    // ---------------------- EXERCISE 5
    // Create a function that takes a list of strings and returns a new list with all strings in lowercase.
    fun transformToLowercase(strings: List<String>): List<String> {
        // Your code here
        return listOf()
    }

    // ---------------------- EXERCISE 6
    // Create a function that prints numbers from 1 to 10, but skips multiples of 3 using a loop and continue statement.
    fun printNumbersSkipMultiplesOf3() {
        // Your code here
    }

    // ---------------------- EXERCISE 7
    // Create a function that takes a string and returns true if it is a palindrome, false otherwise.
    // Palindrome: a word, phrase, or sequence that reads the same backwards as forwards, e.g. madam or nurses run.
    fun isPalindrome(input: String): Boolean {
        // Your code here
        return false
    }

    // ---------------------- EXERCISE 8
    // Modify the function to have default values for width and height and to return the area of the rectangle.
    fun calculateArea(width: Int, height: Int): Int {
        // Your code here
        return 0
    }

    // ---------------------- EXERCISE 9
    // Create a function that takes a day of the week as a string and returns "Weekday" or "Weekend".
    // Make sure it handles both uppercase and lowercase letters.
    // If the day does not exist, return "Invalid day".
    fun dayType(day: String): String {
        return ""
    }

    // ---------------------- EXERCISE 10
    // Create a function that takes an integer year and returns true if it is a leap year, false otherwise.
    // Leap year requirements:
    //      Dividing the year by 4 must result in a whole number with no remainder (no decimals)
    //      If dividing the year by 100 result in a whole number, it must also result in a whole number when dividing by 400
    fun isLeapYear(year: Int): Boolean {
        return false
    }

    // ---------------------- EXERCISE 11
    // Create a function that takes a list of integers and a lambda function, and returns
    // a new list with the results of applying the lambda to each element.
    // Example:
    //      val numbers = listOf(1, 2, 3, 4, 5)
    //      val result = applyLambda(numbers) { it * 2 }
    //      println(result) // Output should be: [2, 4, 6, 8, 10]
    fun applyLambda(numbers: List<Int>, lambda: (Int) -> Int): List<Int> {
        return listOf()
    }
}
