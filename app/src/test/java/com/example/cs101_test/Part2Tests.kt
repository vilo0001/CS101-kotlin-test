package com.example.cs101_test

import com.example.cs101_test.exercises.Part2ConditionalLogic
import org.junit.Assert.*
import org.junit.Assert.fail
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class Part2Tests {
    private val clazz = Part2ConditionalLogic::class.java
    private val instance = Part2ConditionalLogic

    // Helper function to capture stdout
    private fun captureOutput(action: () -> Unit): String {
        val outputStream = ByteArrayOutputStream()
        val printStream = PrintStream(outputStream)
        val originalOut = System.out
        System.setOut(printStream)
        try {
            action.invoke()
        } finally {
            System.setOut(originalOut)
        }
        return outputStream.toString()
    }

    // ---------------------- EXERCISE 1
    @Test
    fun `exercise 01 checkNumber`() {
        try {
            val method = clazz.getDeclaredMethod("checkNumber", Int::class.java)
            method.isAccessible = true

            val positiveResult = method.invoke(instance, 5)
            val negativeResult = method.invoke(instance, -3)
            val zeroResult = method.invoke(instance, 0)

            println("Checking 'checkNumber' function")
            assertEquals("Positive", positiveResult)
            assertEquals("Negative", negativeResult)
            assertEquals("Zero", zeroResult)
        } catch (e: NoSuchMethodException) {
            println("Exercise 1 failed: 'checkNumber' function not found.")
            fail("Exercise 1 failed: 'checkNumber' function not found.")
        } catch (e: Exception) {
            println("Exercise 1 failed: ${e.message}")
            fail("Exercise 1 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 2
    @Test
    fun `exercise 02 printChars`() {
        try {
            val method = clazz.getDeclaredMethod("printChars", String::class.java)
            method.isAccessible = true

            val output = captureOutput {
                method.invoke(instance, "hello")
            }.trim().split("\n").map { it.trim() }

            val expectedOutput = listOf("h", "e", "l", "l", "o")

            println("Checking 'printChars' function")
            assertEquals(expectedOutput, output)
        } catch (e: NoSuchMethodException) {
            println("Exercise 2 failed: 'printChars' function not found.")
            fail("Exercise 2 failed: 'printChars' function not found.")
        } catch (e: Exception) {
            println("Exercise 2 failed: ${e.message}")
            fail("Exercise 2 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 3
    @Test
    fun `exercise 03 sumDigits`() {
        try {
            val method = clazz.getDeclaredMethod("sumDigits", Int::class.java)
            method.isAccessible = true

            val result = method.invoke(instance, 12345)

            println("Checking 'sumDigits' function")
            assertEquals(15, result)
        } catch (e: NoSuchMethodException) {
            println("Exercise 3 failed: 'sumDigits' function not found.")
            fail("Exercise 3 failed: 'sumDigits' function not found.")
        } catch (e: Exception) {
            println("Exercise 3 failed: ${e.message}")
            fail("Exercise 3 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 4
    @Test
    fun `exercise 04 filterOddNumbers`() {
        try {
            val method = clazz.getDeclaredMethod("filterOddNumbers", List::class.java)
            method.isAccessible = true

            val result = method.invoke(instance, listOf(1, 2, 3, 4, 5))

            println("Checking 'filterOddNumbers' function")
            assertEquals(listOf(1, 3, 5), result)
        } catch (e: NoSuchMethodException) {
            println("Exercise 4 failed: 'filterOddNumbers' function not found.")
            fail("Exercise 4 failed: 'filterOddNumbers' function not found.")
        } catch (e: Exception) {
            println("Exercise 4 failed: ${e.message}")
            fail("Exercise 4 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 5
    @Test
    fun `exercise 05 transformToLowercase`() {
        try {
            val method = clazz.getDeclaredMethod("transformToLowercase", List::class.java)
            method.isAccessible = true

            val result = method.invoke(instance, listOf("Hello", "WORLD"))

            println("Checking 'transformToLowercase' function")
            assertEquals(listOf("hello", "world"), result)
        } catch (e: NoSuchMethodException) {
            println("Exercise 5 failed: 'transformToLowercase' function not found.")
            fail("Exercise 5 failed: 'transformToLowercase' function not found.")
        } catch (e: Exception) {
            println("Exercise 5 failed: ${e.message}")
            fail("Exercise 5 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 6
    @Test
    fun `exercise 06 printNumbersSkipMultiplesOf3`() {
        try {
            val method = clazz.getDeclaredMethod("printNumbersSkipMultiplesOf3")
            method.isAccessible = true

            val output = captureOutput {
                method.invoke(instance)
            }.trim().split("\n").map{ it.trim() }

            val expectedOutput = listOf("1", "2", "4", "5", "7", "8", "10")
            assertEquals(expectedOutput, output)
        } catch (e: NoSuchMethodException) {
            println("Exercise 6 failed: 'printNumbersSkipMultiplesOf3' function not found.")
            fail("Exercise 6 failed: 'printNumbersSkipMultiplesOf3' function not found.")
        } catch (e: Exception) {
            println("Exercise 6 failed: ${e.message}")
            fail("Exercise 6 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 7
    @Test
    fun `exercise 07 isPalindrome`() {
        try {
            val method = clazz.getDeclaredMethod("isPalindrome", String::class.java)
            method.isAccessible = true

            val palindromeResult = method.invoke(instance, "madam")
            val nonPalindromeResult = method.invoke(instance, "hello")

            println("Checking 'isPalindrome' function")
            assertEquals(true, palindromeResult)
            assertEquals(false, nonPalindromeResult)
        } catch (e: NoSuchMethodException) {
            println("Exercise 7 failed: 'isPalindrome' function not found.")
            fail("Exercise 7 failed: 'isPalindrome' function not found.")
        } catch (e: Exception) {
            println("Exercise 7 failed: ${e.message}")
            fail("Exercise 7 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 8

        @Test
        fun `exercise 08 test calculateArea`() {
            try {
                val methodWithArgs = clazz.getDeclaredMethod("calculateArea", Int::class.java, Int::class.java)
                methodWithArgs.isAccessible = true

                // Test with provided values
                val areaResultWithValues = methodWithArgs.invoke(instance, 5, 10) as Int
                println("Checking 'calculateArea' function with provided values")
                if (areaResultWithValues != 50) {
                    fail("Failed: calculateArea with provided values")
                }
                assertEquals(50, areaResultWithValues)

                // Check for default parameter method
                val defaultMethod = clazz.declaredMethods.find { it.name == "calculateArea\$default" }

                if (defaultMethod != null) {
                    println("Checking 'calculateArea' function with default values")

                    // Mask for width defaulted: binary 01 (decimal 1)
                    val widthDefaultMask = 0b01
                    val areaResultWithWidthDefault = defaultMethod.invoke(instance, instance, 0, 10, widthDefaultMask, null) as Int
                    if (areaResultWithWidthDefault != 50) {
                        fail("Failed: Width default parameter not set correctly")
                    }
                    assertEquals(50, areaResultWithWidthDefault)

                    // Mask for height defaulted: binary 10 (decimal 2)
                    val heightDefaultMask = 0b10
                    val areaResultWithHeightDefault = defaultMethod.invoke(instance, instance, 5, 0, heightDefaultMask, null) as Int
                    if (areaResultWithHeightDefault != 50) {
                        fail("Failed: Height default parameter not set correctly")
                    }
                    assertEquals(50, areaResultWithHeightDefault)
                } else {
                    fail("Exercise 8 failed: 'calculateArea' function does not have both default parameters.")
                }

            } catch (e: NoSuchMethodException) {
                println("Exercise 8 failed: 'calculateArea' function not found.")
                fail("Exercise 8 failed: 'calculateArea' function not found.")
            } catch (e: Exception) {
                println("Exercise 8 failed: ${e.message}")
                fail("Exercise 8 failed: ${e.message}")
            }
        }

    // ---------------------- EXERCISE 9
    @Test
    fun `exercise 09 dayType`() {
        try {
            val method = clazz.getDeclaredMethod("dayType", String::class.java)
            method.isAccessible = true

            val weekdayResult = method.invoke(instance, "Monday")
            val weekendResult = method.invoke(instance, "saturday")
            val invalidResult = method.invoke(instance, "Funday")

            println("Checking 'dayType' function")
            assertEquals("Weekday", weekdayResult)
            assertEquals("Weekend", weekendResult)
            assertEquals("Invalid day", invalidResult)
        } catch (e: NoSuchMethodException) {
            println("Exercise 9 failed: 'dayType' function not found.")
            fail("Exercise 9 failed: 'dayType' function not found.")
        } catch (e: Exception) {
            println("Exercise 9 failed: ${e.message}")
            fail("Exercise 9 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 10
    @Test
    fun `exercise 10 isLeapYear`() {
        try {
            val method = clazz.getDeclaredMethod("isLeapYear", Int::class.java)
            method.isAccessible = true

            val leapYear = method.invoke(instance, 2024) as Boolean
            val nonLeapYear = method.invoke(instance, 2023) as Boolean
            val centuryNonLeapYear = method.invoke(instance, 1900) as Boolean
            val centuryLeapYear = method.invoke(instance, 2000) as Boolean

            println("Checking 'isLeapYear' function")
            assertTrue(leapYear)
            assertFalse(nonLeapYear)
            assertFalse(centuryNonLeapYear)
            assertTrue(centuryLeapYear)
        } catch (e: NoSuchMethodException) {
            println("Exercise 10 failed: 'isLeapYear' function not found.")
            fail("Exercise 10 failed: 'isLeapYear' function not found.")
        } catch (e: Exception) {
            println("Exercise 10 failed: ${e.message}")
            fail("Exercise 10 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 11
    @Test
    fun `exercise 11 applyLambda`() {
        try {
            val method = clazz.getDeclaredMethod("applyLambda", List::class.java, Function1::class.java)
            method.isAccessible = true

            val numbers = listOf(1, 2, 3, 4, 5)
            val lambda = { x: Int -> x * 2 }
            val result = method.invoke(instance, numbers, lambda) as List<Int>

            println("Checking 'applyLambda' function")
            assertEquals(listOf(2, 4, 6, 8, 10), result)
        } catch (e: NoSuchMethodException) {
            println("Exercise 11 failed: 'applyLambda' function not found.")
            fail("Exercise 11 failed: 'applyLambda' function not found.")
        } catch (e: Exception) {
            println("Exercise 11 failed: ${e.message}")
            fail("Exercise 11 failed: ${e.message}")
        }
    }

}