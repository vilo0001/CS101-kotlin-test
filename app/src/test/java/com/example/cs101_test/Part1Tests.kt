package com.example.cs101_test

import com.example.cs101_test.exercises.Part1Basics
import com.example.cs101_test.exercises.Part1Basics.printVariables
import org.junit.Assert.*
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.lang.reflect.InvocationTargetException

class Part1Tests {
    private val clazz = Part1Basics::class.java
    private val instance = Part1Basics

    // ---------------------- EXERCISE 1
    @Test
    fun `exercise 01 test mutable variable temperature`() {
        try {
            val temperatureField = clazz.getDeclaredField("temperature")
            temperatureField.isAccessible = true
            var temperature = temperatureField.getInt(null)

            println("Checking mutable variable 'temperature' declaration and assignment")
            assertNotNull(temperature)
            assertEquals(25, temperature)

            // Verify mutability
            temperatureField.setInt(null, 30)
            temperature = temperatureField.getInt(null)
            println("Checking if mutable variable 'temperature' can be reassigned")
            assertEquals(30, temperature)
            // Return to old value
            temperatureField.setInt(null, 25)
        } catch (e: NoSuchFieldException) {
            println("Exercise 1 failed: 'temperature' variable not found.")
            fail("Exercise 1 failed: 'temperature' variable not found.")
        } catch (e: Exception) {
            println("Exercise 1 failed: ${e.message}")
            fail("Exercise 1 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 2
    @Test
    fun `exercise 02 test immutable variable greeting`() {
        try {
            val greetingField = clazz.getDeclaredField("greeting")
            greetingField.isAccessible = true
            val greeting = greetingField.get(null) as String

            println("Checking immutable variable 'greeting' declaration and assignment")
            assertNotNull(greeting)
            assertEquals("Hello, Kotlin!", greeting)

            // Verify immutability
            try {
                greetingField.set(null, "Hi, Kotlin!")
                println("Exercise 2 failed: Immutable variable 'greeting' should not be modifiable")
                fail("Exercise 2 failed: Immutable variable 'greeting' should not be modifiable")
            } catch (e: IllegalAccessException) {
                println("Immutable variable 'greeting' cannot be reassigned")
            }
        } catch (e: NoSuchFieldException) {
            println("Exercise 2 failed: 'greeting' variable not found.")
            fail("Exercise 2 failed: 'greeting' variable not found.")
        } catch (e: Exception) {
            println("Exercise 2 failed: ${e.message}")
            fail("Exercise 2 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 3
    @Test
    fun `exercise 03 test variable printing`() {
        try {
            println("Checking if 'temperature' and 'greeting' is printed correctly")
            // Capture the output
            val outputStream = ByteArrayOutputStream()
            val originalOut = System.out
            System.setOut(PrintStream(outputStream))

            val temperatureField = clazz.getDeclaredField("temperature")
            temperatureField.isAccessible = true
            val temperature = temperatureField.getInt(null)

            val greetingField = clazz.getDeclaredField("greeting")
            greetingField.isAccessible = true
            val greeting = greetingField.get(null) as String

            printVariables() // Call the function that prints the variables

            // Restore the original System.out
            System.setOut(originalOut)

            // Verify the print statements
            val output = outputStream.toString().trim().split("\n")
            assertEquals("25", output[0].trim())
            assertEquals("Hello, Kotlin!", output[1].trim())
        } catch (e: NoSuchFieldException) {
            println("Exercise 3 failed: 'temperature' or 'greeting' variable not found.")
            fail("Exercise 3 failed: 'temperature' or 'greeting' variable not found.")
        } catch (e: Exception) {
            println("Exercise 3 failed: ${e.message}")
            fail("Exercise 3 failed: ${e.message}")
        }
    }


    // ---------------------- EXERCISE 4
    @Test
    fun `exercise 04 test convertToUpperCase function`() {
        try {
            val convertToUpperCaseMethod = clazz.getDeclaredMethod("convertToUpperCase", String::class.java)
            convertToUpperCaseMethod.isAccessible = true

            val result = convertToUpperCaseMethod.invoke(instance, "kotlin") as String

            println("Checking the function converts the string to uppercase correctly")
            assertEquals("KOTLIN", result)
        } catch (e: NoSuchMethodException) {
            println("Exercise 4 failed: 'convertToUpperCase' method not found.")
            fail("Exercise 4 failed: 'convertToUpperCase' method not found.")
        } catch (e: Exception) {
            println("Exercise 4 failed: ${e.message}")
            fail("Exercise 4 failed: ${e.message}")
        }
    }



    // ---------------------- EXERCISE 5
    @Test
    fun `exercise 05 test isEven function`() {
        try {
            val isEvenMethod = clazz.getDeclaredMethod("isEven", Int::class.java)
            isEvenMethod.isAccessible = true

            println("Checking the function correctly identifies even numbers")
            assertTrue(isEvenMethod.invoke(instance, 2) as Boolean)
            assertTrue(isEvenMethod.invoke(instance, 4) as Boolean)

            println("Checking the function correctly identifies odd numbers")
            assertFalse(isEvenMethod.invoke(instance, 1) as Boolean)
            assertFalse(isEvenMethod.invoke(instance, 3) as Boolean)
        } catch (e: NoSuchMethodException) {
            println("Exercise 5 failed: 'isEven' method not found.")
            fail("Exercise 5 failed: 'isEven' method not found.")
        } catch (e: Exception) {
            println("Exercise 5 failed: ${e.message}")
            fail("Exercise 5 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 6
    @Test
    fun `exercise 06 test secondLargest function`() {
        try {
            val method = Part1Basics::class.java.getDeclaredMethod("secondLargest", List::class.java)
            method.isAccessible = true

            // Test with a regular list
            var result = method.invoke(Part1Basics, listOf(1, 2, 3, 4, 5)) as Int
            assertEquals(4, result)

            // Test with duplicate values
            result = method.invoke(Part1Basics, listOf(5, 1, 5, 3, 4)) as Int
            assertEquals(5, result)

            // Test with negative values
            result = method.invoke(Part1Basics, listOf(-1, -2, -3, -4, -5)) as Int
            assertEquals(-2, result)

            // Test with a list of two elements
            result = method.invoke(Part1Basics, listOf(10, 20)) as Int
            assertEquals(10, result)

            // Test with a large list
            result = method.invoke(Part1Basics, listOf(100, 99, 98, 97, 96, 95, 94)) as Int
            assertEquals(99, result)

            // Edge cases
            // Test with a list with one element should throw an exception
            try {
                method.invoke(Part1Basics, listOf(42))
                fail("Exercise 6 failed: Expected IndexOutOfBoundsException for single element list")
            } catch (e: InvocationTargetException) {
                assertTrue(e.targetException is IndexOutOfBoundsException)
            }

            // Test with an empty list should throw an exception
            try {
                method.invoke(Part1Basics, emptyList<Int>())
                fail("Exercise 6 failed: Expected IndexOutOfBoundsException for empty list")
            } catch (e: InvocationTargetException) {
                assertTrue(e.targetException is IndexOutOfBoundsException)
            }
        } catch (e: NoSuchMethodException) {
            println("Exercise 6 failed: 'secondLargest' method not found.")
            fail("Exercise 6 failed: 'secondLargest' method not found.")
        } catch (e: Exception) {
            println("Exercise 6 failed: ${e.message}")
            fail("Exercise 6 failed: ${e.message}")
        }
    }
    // ---------------------- EXERCISE 7
    @Test
    fun `exercise 07 test greetUser function`() {
        try {
            val method = Part1Basics::class.java.getDeclaredMethod("greetUser", String::class.java)
            method.isAccessible = true

            val result = method.invoke(Part1Basics, "Alice") as String
            assertEquals("Hello, Alice!", result)
        } catch (e: NoSuchMethodException) {
            println("Exercise 7 failed: 'greetUser' method not found.")
            fail("Exercise 7 failed: 'greetUser' method not found.")
        } catch (e: Exception) {
            println("Exercise 7 failed: ${e.message}")
            fail("Exercise 7 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 8
    @Test
    fun `exercise 08 test findMax function`() {
        try {
            val method = Part1Basics::class.java.getDeclaredMethod("findMax", List::class.java)
            method.isAccessible = true

            var result = method.invoke(Part1Basics, listOf(1, 2, 3, 4, 5)) as Int
            assertEquals(5, result)

            result = method.invoke(Part1Basics, listOf(-1, -2, -3, -4, -5)) as Int
            assertEquals(-1, result)

            try {
                method.invoke(Part1Basics, emptyList<Int>())
                fail("Exercise 8 failed: Expected IllegalArgumentException for empty list")
            } catch (e: InvocationTargetException) {
                assertTrue(e.targetException is IllegalArgumentException)
            }
        } catch (e: NoSuchMethodException) {
            println("Exercise 8 failed: 'findMax' method not found.")
            fail("Exercise 8 failed: 'findMax' method not found.")
        } catch (e: Exception) {
            println("Exercise 8 failed: ${e.message}")
            fail("Exercise 8 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 9
    @Test
    fun `exercise 09 test getValue function`() {
        try {
            val method = Part1Basics::class.java.getDeclaredMethod("getValue", Map::class.java, String::class.java)
            method.isAccessible = true

            val map = mapOf("a" to 1, "b" to 2, "c" to 3)

            var result = method.invoke(Part1Basics, map, "b") as Int?
            assertEquals(2, result)

            result = method.invoke(Part1Basics, map, "d") as Int?
            assertNull(result)
        } catch (e: NoSuchMethodException) {
            println("Exercise 9 failed: 'getValue' method not found.")
            fail("Exercise 9 failed: 'getValue' method not found.")
        } catch (e: Exception) {
            println("Exercise 9 failed: ${e.message}")
            fail("Exercise 9 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 10
    @Test
    fun `exercise 10 test calculateArea function`() {
        try {
            val method = Part1Basics::class.java.getDeclaredMethod("calculateArea", Double::class.java, Double::class.java)
            method.isAccessible = true

            val result = method.invoke(Part1Basics, 5.0, 10.0) as Double
            assertEquals(50.0, result, 0.0)
        } catch (e: NoSuchMethodException) {
            println("Exercise 10 failed: 'calculateArea' method not found.")
            fail("Exercise 10 failed: 'calculateArea' method not found.")
        } catch (e: Exception) {
            println("Exercise 10 failed: ${e.message}")
            fail("Exercise 10 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 11
    @Test
    fun `exercise 11 test isPositive function`() {
        try {
            val method = Part1Basics::class.java.getDeclaredMethod("isPositive", Int::class.java)
            method.isAccessible = true

            var result = method.invoke(Part1Basics, 10) as Boolean
            assertTrue(result)

            result = method.invoke(Part1Basics, -10) as Boolean
            assertFalse(result)

            result = method.invoke(Part1Basics, 0) as Boolean
            assertFalse(result)
        } catch (e: NoSuchMethodException) {
            println("Exercise 11 failed: 'isPositive' method not found.")
            fail("Exercise 11 failed: 'isPositive' method not found.")
        } catch (e: Exception) {
            println("Exercise 11 failed: ${e.message}")
            fail("Exercise 11 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 12
    @Test
    fun `exercise 12 test calculateAverage function`() {
        try {
            val method = Part1Basics::class.java.getDeclaredMethod("calculateAverage", Array<Double>::class.java)
            method.isAccessible = true

            val result = method.invoke(Part1Basics, arrayOf(1.0, 2.0, 3.0, 4.0, 5.0)) as Double
            assertEquals(3.0, result, 0.0)
        } catch (e: NoSuchMethodException) {
            println("Exercise 12 failed: 'calculateAverage' method not found.")
            fail("Exercise 12 failed: 'calculateAverage' method not found.")
        } catch (e: Exception) {
            println("Exercise 12 failed: ${e.message}")
            fail("Exercise 12 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 13
    @Test
    fun `exercise 13 test filterNegativeNumbers function`() {
        try {
            val method = Part1Basics::class.java.getDeclaredMethod("filterNegativeNumbers", List::class.java)
            method.isAccessible = true

            val result = method.invoke(Part1Basics, listOf(1, -2, 3, -4, 5)) as List<Int>
            assertEquals(listOf(1, 3, 5), result)
        } catch (e: NoSuchMethodException) {
            println("Exercise 13 failed: 'filterNegativeNumbers' method not found.")
            fail("Exercise 13 failed: 'filterNegativeNumbers' method not found.")
        } catch (e: Exception) {
            println("Exercise 13 failed: ${e.message}")
            fail("Exercise 13 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 14
    @Test
    fun `exercise 14 test charFrequency function`() {
        try {
            val method = Part1Basics::class.java.getDeclaredMethod("charFrequency", String::class.java)
            method.isAccessible = true

            val result = method.invoke(Part1Basics, "hello") as Map<Char, Int>
            assertEquals(mapOf('h' to 1, 'e' to 1, 'l' to 2, 'o' to 1), result)
        } catch (e: NoSuchMethodException) {
            println("Exercise 14 failed: 'charFrequency' method not found.")
            fail("Exercise 14 failed: 'charFrequency' method not found.")
        } catch (e: Exception) {
            println("Exercise 14 failed: ${e.message}")
            fail("Exercise 14 failed: ${e.message}")
        }
    }

}
