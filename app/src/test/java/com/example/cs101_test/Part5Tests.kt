package com.example.cs101_test

import com.example.cs101_test.exercises.Part5InterfacesAndAbstractClasses
import org.junit.jupiter.api.Assertions.*
import org.junit.Test
import org.junit.jupiter.api.fail
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class Part5Tests {

    private val clazz = Part5InterfacesAndAbstractClasses::class.java

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
    fun `exercise 01 testMediaContentClass`() {
        println("Starting test for MediaContent class and its subclasses")

        // Attempt to load the MediaContent class as a nested class
        val mediaContentClass = try {
            clazz.declaredClasses.first { it.simpleName == "MediaContent" }
        } catch (e: NoSuchElementException) {
            null
        }

        // Ensure the MediaContent class is found
        if (mediaContentClass == null) {
            println("Exercise 1 failed: 'MediaContent' class not found.")
            fail("Exercise 1 failed: 'MediaContent' class not found.")
        }

        // Attempt to load the Movie class as a nested class
        val movieClass = try {
            clazz.declaredClasses.first { it.simpleName == "Movie" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (movieClass == null) {
            println("Exercise 1 failed: 'Movie' class not found.")
            fail("Exercise 1 failed: 'Movie' class not found.")
        }

        // Attempt to load the Series class as a nested class
        val seriesClass = try {
            clazz.declaredClasses.first { it.simpleName == "Series" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (seriesClass == null) {
            println("Exercise 1 failed: 'Series' class not found.")
            fail("Exercise 1 failed: 'Series' class not found.")
        }

        // Attempt to load the Documentary class as a nested class
        val documentaryClass = try {
            clazz.declaredClasses.first { it.simpleName == "Documentary" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (documentaryClass == null) {
            println("Exercise 1 failed: 'Documentary' class not found.")
            fail("Exercise 1 failed: 'Documentary' class not found.")
        }

        try {
            println("Creating instances for each subclass...")

            // Create instances of the subclasses and test their methods
            val movieConstructor = movieClass.getConstructor(
                String::class.java,
                Int::class.java,
                String::class.java,
                Double::class.java,
                String::class.java
            )
            val movieInstance = movieConstructor.newInstance("Inception", 2010, "Sci-Fi", 8.8, "Christopher Nolan")

            val seriesConstructor = seriesClass.getConstructor(
                String::class.java,
                Int::class.java,
                String::class.java,
                Double::class.java,
                Int::class.java
            )
            val seriesInstance = seriesConstructor.newInstance("Breaking Bad", 2008, "Drama", 9.5, 5)

            val documentaryConstructor = documentaryClass.getConstructor(
                String::class.java,
                Int::class.java,
                String::class.java,
                Double::class.java,
                String::class.java
            )
            val documentaryInstance = documentaryConstructor.newInstance("Planet Earth", 2006, "Nature", 9.4, "Wildlife")

            val playContentMethod = mediaContentClass.getMethod("playContent")

            println("Testing 'MediaContent' class and its methods")
            assertNotNull(mediaContentClass)
            assertNotNull(movieInstance)
            assertNotNull(seriesInstance)
            assertNotNull(documentaryInstance)
            assertNotNull(playContentMethod)

            println("Testing playContent() in Movie")
            // Test playContent method in Movie
            val moviePlayOutput = captureOutput {
                playContentMethod.invoke(movieInstance)
            }
            println(moviePlayOutput)
            assertTrue(
                moviePlayOutput.contains("Inception"),
                "Expected output to contain 'Inception'."
            )

            println("Testing playContent() in Series")
            // Test playContent method in Series
            val seriesPlayOutput = captureOutput {
                playContentMethod.invoke(seriesInstance)
            }
            println(seriesPlayOutput)
            assertTrue(
                seriesPlayOutput.contains("Breaking Bad"),
                "Expected output to contain 'Breaking Bad'."
            )

            println("Testing playContent() in Documentary")
            // Test playContent method in Documentary
            val documentaryPlayOutput = captureOutput {
                playContentMethod.invoke(documentaryInstance)
            }
            println(documentaryPlayOutput)
            assertTrue(
                documentaryPlayOutput.contains("Planet Earth"),
                "Expected output to contain 'Planet Earth'."
            )

        } catch (e: NoSuchMethodException) {
            println("Exercise 1 failed: Method not found ${e.message?.split("MediaContent\$")?.getOrNull(1) ?: e.message}")
            fail("Exercise 1 failed: Method not found ${e.message?.split("MediaContent\$")?.getOrNull(1) ?: e.message}")
        } catch (e: Exception) {
            println("Exercise 1 failed: ${e.message}")
            fail("Exercise 1 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 2
    @Test
    fun `exercise 02 testAccountInterface`() {
        println("Starting test for Account interface and its implementing classes")

        // Attempt to load the Account interface
        val accountInterface = try {
            clazz.declaredClasses.first { it.simpleName == "Account" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (accountInterface == null) {
            println("Exercise 2 failed: 'Account' interface not found.")
            fail("Exercise 2 failed: 'Account' interface not found.")
        }

        // Attempt to load the SavingsAccount class
        val savingsAccountClass = try {
            clazz.declaredClasses.first { it.simpleName == "SavingsAccount" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (savingsAccountClass == null) {
            println("Exercise 2 failed: 'SavingsAccount' class not found.")
            fail("Exercise 2 failed: 'SavingsAccount' class not found.")
        }

        // Attempt to load the CurrentAccount class
        val currentAccountClass = try {
            clazz.declaredClasses.first { it.simpleName == "CurrentAccount" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (currentAccountClass == null) {
            println("Exercise 2 failed: 'CurrentAccount' class not found.")
            fail("Exercise 2 failed: 'CurrentAccount' class not found.")
        }

        // Attempt to load the BusinessAccount class
        val businessAccountClass = try {
            clazz.declaredClasses.first { it.simpleName == "BusinessAccount" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (businessAccountClass == null) {
            println("Exercise 2 failed: 'BusinessAccount' class not found.")
            fail("Exercise 2 failed: 'BusinessAccount' class not found.")
        }

        try {
            println("Creating instances for each account type...")

            // Create instances of the account classes and test their methods
            val savingsAccountInstance = savingsAccountClass.let {
                val savingsAccountConstructor = it.getConstructor(
                    String::class.java,
                    String::class.java,
                    Double::class.java,
                    Double::class.java
                )
                savingsAccountConstructor.newInstance("SA123", "Alice", 1000.0, 0.02)
            }.also { instance ->
                if (!accountInterface.isAssignableFrom(instance::class.java)) {
                    fail("Exercise 2 failed: 'SavingsAccount' does not implement 'Account' interface.")
                }
            }

            val currentAccountInstance = currentAccountClass.let {
                val currentAccountConstructor = it.getConstructor(
                    String::class.java,
                    String::class.java,
                    Double::class.java,
                    Double::class.java
                )
                currentAccountConstructor.newInstance("CA123", "Bob", 500.0, 200.0)
            }.also { instance ->
                if (!accountInterface.isAssignableFrom(instance::class.java)) {
                    fail("Exercise 2 failed: 'CurrentAccount' does not implement 'Account' interface.")
                }
            }

            val businessAccountInstance = businessAccountClass.let {
                val businessAccountConstructor = it.getConstructor(
                    String::class.java,
                    String::class.java,
                    Double::class.java,
                    String::class.java
                )
                businessAccountConstructor.newInstance("BA123", "Charlie", 2000.0, "Charlie Inc")
            }.also { instance ->
                if (!accountInterface.isAssignableFrom(instance::class.java)) {
                    fail("Exercise 2 failed: 'BusinessAccount' does not implement 'Account' interface.")
                }
            }

            println("Testing 'Account' interface and its methods")
            assertNotNull(savingsAccountInstance)
            assertNotNull(currentAccountInstance)
            assertNotNull(businessAccountInstance)

            // Check for method existence
            val checkBalanceMethod = accountInterface.getMethod("checkBalance")
            val depositMethod = accountInterface.getMethod("deposit", Double::class.java)
            val withdrawMethod = accountInterface.getMethod("withdraw", Double::class.java)

            println("Testing checkBalance in SavingsAccount")
            val savingsBalance = checkBalanceMethod.invoke(savingsAccountInstance) as Double
            assertEquals(1000.0, savingsBalance, 0.001, "Expected SavingsAccount balance to be 1000.0.")

            // Testing deposit and withdraw methods
            println("Testing deposit() in SavingsAccount")
            depositMethod.invoke(savingsAccountInstance, 500.0)
            assertEquals(1500.0, checkBalanceMethod.invoke(savingsAccountInstance) as Double)

            println("Testing withdraw() in SavingsAccount")
            withdrawMethod.invoke(savingsAccountInstance, 200.0)
            assertEquals(1300.0, checkBalanceMethod.invoke(savingsAccountInstance) as Double)

            println("Testing insufficient funds in SavingsAccount")
            val savingsOutput = captureOutput { withdrawMethod.invoke(savingsAccountInstance, 1500.0) }
            assertEquals(1300.0, checkBalanceMethod.invoke(savingsAccountInstance) as Double, "Balance should not change when withdrawal exceeds balance")
            assertTrue(savingsOutput.contains("Insufficient funds"), "Expected 'Insufficient funds' message")

            println("Testing checkBalance in CurrentAccount")
            val currentAccountBalance = checkBalanceMethod.invoke(currentAccountInstance) as Double
            assertEquals(500.0, currentAccountBalance, 0.001, "Expected CurrentAccount balance to be 500.0.")

            println("Testing deposit() in CurrentAccount")
            depositMethod.invoke(currentAccountInstance, 300.0)
            assertEquals(800.0, checkBalanceMethod.invoke(currentAccountInstance) as Double)

            println("Testing withdraw() in CurrentAccount with overdraft")
            println("withdrawing 900 from a balance = 800 and overdraftLimit=200")
            withdrawMethod.invoke(currentAccountInstance, 900.0)
            assertEquals(-100.0, checkBalanceMethod.invoke(currentAccountInstance) as Double)

            println("Testing insufficient funds in CurrentAccount")
            val currentAccountOutput = captureOutput { withdrawMethod.invoke(currentAccountInstance, 200.0) }
            assertEquals(-100.0, checkBalanceMethod.invoke(currentAccountInstance) as Double, "Balance should not change when withdrawal exceeds balance and overdraft limit")
            assertTrue(currentAccountOutput.contains("Insufficient funds"), "Expected 'Insufficient funds' message")

            println("Testing checkBalance in BusinessAccount")
            val businessBalance = checkBalanceMethod.invoke(businessAccountInstance) as Double
            assertEquals(2000.0, businessBalance, 0.001, "Expected BusinessAccount balance to be 2000.0.")

            println("Testing deposit() in BusinessAccount")
            depositMethod.invoke(businessAccountInstance, 1000.0)
            assertEquals(3000.0, checkBalanceMethod.invoke(businessAccountInstance) as Double)

            println("Testing withdraw() in BusinessAccount")
            withdrawMethod.invoke(businessAccountInstance, 1500.0)
            assertEquals(1500.0, checkBalanceMethod.invoke(businessAccountInstance) as Double)

            println("Testing insufficient funds in BusinessAccount")
            val businessAccountOutput = captureOutput { withdrawMethod.invoke(businessAccountInstance, 2000.0) }
            assertEquals(1500.0, checkBalanceMethod.invoke(businessAccountInstance) as Double, "Balance should not change when withdrawal exceeds balance")
            assertTrue(businessAccountOutput.contains("Insufficient funds"), "Expected 'Insufficient funds' message")

        } catch (e: NoSuchMethodException) {
            println("Exercise 2 failed: Method not found ${e.message?.split("Part5InterfacesAndAbstractClasses\$")?.getOrNull(1) ?: e.message}")
            fail("Exercise 2 failed: Method not found ${e.message?.split("Part5InterfacesAndAbstractClasses\$")?.getOrNull(1) ?: e.message}")
        } catch (e: Exception) {
            println("Exercise 2 failed: ${e.message}")
            fail("Exercise 2 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 3
    @Test
    fun `exercise 03 testApplianceInterface`() {
        println("Starting test for Appliance interface and its implementing classes")

        // Attempt to load the Appliance interface
        val applianceInterface = try {
            clazz.declaredClasses.first { it.simpleName == "Appliance" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (applianceInterface == null) {
            println("Exercise 3 failed: 'Appliance' interface not found.")
            fail("Exercise 3 failed: 'Appliance' interface not found.")
        }

        // Attempt to load the WashingMachine class
        val washingMachineClass = try {
            clazz.declaredClasses.first { it.simpleName == "WashingMachine" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (washingMachineClass == null) {
            println("Exercise 3 failed: 'WashingMachine' class not found.")
            fail("Exercise 3 failed: 'WashingMachine' class not found.")
        }

        // Attempt to load the Refrigerator class
        val refrigeratorClass = try {
            clazz.declaredClasses.first { it.simpleName == "Refrigerator" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (refrigeratorClass == null) {
            println("Exercise 3 failed: 'Refrigerator' class not found.")
            fail("Exercise 3 failed: 'Refrigerator' class not found.")
        }

        try {
            println("Creating instances for each appliance type...")

            // Create instances of the appliance classes and test their methods
            val washingMachineInstance = washingMachineClass.let {
                val washingMachineConstructor = it.getConstructor(
                    String::class.java,
                    Double::class.java,
                    Boolean::class.java,
                    Double::class.java
                )
                washingMachineConstructor.newInstance("LG", 1500.0, false, 7.0)
            }.also { instance ->
                if (!applianceInterface.isAssignableFrom(instance::class.java)) {
                    fail("Exercise 3 failed: 'WashingMachine' does not implement 'Appliance' interface.")
                }
            }

            val refrigeratorInstance = refrigeratorClass.let {
                val refrigeratorConstructor = it.getConstructor(
                    String::class.java,
                    Double::class.java,
                    Boolean::class.java,
                    Double::class.java
                )
                refrigeratorConstructor.newInstance("Samsung", 1200.0, false, 4.0)
            }.also { instance ->
                if (!applianceInterface.isAssignableFrom(instance::class.java)) {
                    fail("Exercise 3 failed: 'Refrigerator' does not implement 'Appliance' interface.")
                }
            }

            println("Testing 'Appliance' interface and its methods")

            // Check for method existence
            val turnOnMethod = applianceInterface.getMethod("turnOn")
            val turnOffMethod = applianceInterface.getMethod("turnOff")
            val showStatusMethod = applianceInterface.getMethod("showStatus")

            // Testing turnOn, turnOff, and showStatus methods
            println("Testing turnOn() and turnOff() in WashingMachine")
            turnOnMethod.invoke(washingMachineInstance)
            assertTrue(washingMachineInstance.javaClass.getMethod("isOn").invoke(washingMachineInstance) as Boolean, "Expected washing machine to be 'On' after turnOn()")
            turnOffMethod.invoke(washingMachineInstance)
            assertFalse(washingMachineInstance.javaClass.getMethod("isOn").invoke(washingMachineInstance) as Boolean, "Expected washing machine to be 'Off' after turnOff()")

            println("Testing showStatus() in WashingMachine")
            val washingMachineStatusOutput = captureOutput {
                showStatusMethod.invoke(washingMachineInstance)
            }
            println(washingMachineStatusOutput)
            assertTrue(washingMachineStatusOutput.contains("Off", ignoreCase = true), "Expected status to contain 'Off'")

            println("Testing turnOn() and turnOff() in Refrigerator")
            turnOnMethod.invoke(refrigeratorInstance)
            assertTrue(refrigeratorInstance.javaClass.getMethod("isOn").invoke(refrigeratorInstance) as Boolean, "Expected refrigerator to be 'On' after turnOn()")
            turnOffMethod.invoke(refrigeratorInstance)
            assertFalse(refrigeratorInstance.javaClass.getMethod("isOn").invoke(refrigeratorInstance) as Boolean, "Expected refrigerator to be 'Off' after turnOff()")

            println("Testing showStatus() in Refrigerator")
            val refrigeratorStatusOutput = captureOutput {
                showStatusMethod.invoke(refrigeratorInstance)
            }
            println(refrigeratorStatusOutput)
            assertTrue(refrigeratorStatusOutput.contains("Off", ignoreCase = true), "Expected status to contain 'Off'")

        } catch (e: NoSuchMethodException) {
            println("Exercise 3 failed: Method not found ${e.message?.split("Part5InterfacesAndAbstractClasses\$")?.getOrNull(1) ?: e.message}")
            fail("Exercise 3 failed: Method not found ${e.message?.split("Part5InterfacesAndAbstractClasses\$")?.getOrNull(1) ?: e.message}")
        } catch (e: Exception) {
            println("Exercise 3 failed: ${e.message}")
            fail("Exercise 3 failed: ${e.message}")
        }
    }



}