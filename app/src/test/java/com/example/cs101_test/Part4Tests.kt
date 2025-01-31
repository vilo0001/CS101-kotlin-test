package com.example.cs101_test

import com.example.cs101_test.exercises.Part4InheritanceAndAccessModification
import org.junit.jupiter.api.Assertions.*
import org.junit.Test
import org.junit.jupiter.api.fail
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.lang.reflect.Modifier

class Part4Tests {

    private val clazz = Part4InheritanceAndAccessModification::class.java

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
    fun `exercise 01 testApplianceClass`() {
        val applianceClass = try {
            clazz.declaredClasses.first { it.simpleName == "Appliance" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (applianceClass == null) {
            println("Exercise 1 failed: 'Appliance' class not found.")
            fail("Exercise 1 failed: 'Appliance' class not found.")
        }

        val washingMachineClass = try {
            clazz.declaredClasses.first { it.simpleName == "WashingMachine" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (washingMachineClass == null) {
            println("Exercise 1 failed: 'WashingMachine' class not found.")
            fail("Exercise 1 failed: 'WashingMachine' class not found.")
        }

        val refrigeratorClass = try {
            clazz.declaredClasses.first { it.simpleName == "Refrigerator" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (refrigeratorClass == null) {
            println("Exercise 1 failed: 'Refrigerator' class not found.")
            fail("Exercise 1 failed: 'Refrigerator' class not found.")
        }

        try {
            val applianceConstructor = applianceClass.getConstructor(String::class.java, Int::class.java, Boolean::class.java)
            val applianceInstance = applianceConstructor.newInstance("GenericBrand", 1000, false)
            val washingMachineConstructor = washingMachineClass.getConstructor(String::class.java, Int::class.java)
            val washingMachineInstance = washingMachineConstructor.newInstance("LG", 2000)
            val refrigeratorConstructor = refrigeratorClass.getConstructor(String::class.java, Int::class.java)
            val refrigeratorInstance = refrigeratorConstructor.newInstance("Samsung", 1500)

            val turnOnMethod = applianceClass.getMethod("turnOn")
            val turnOffMethod = applianceClass.getMethod("turnOff")
            val toStringMethod = applianceClass.getMethod("toString")

            println("Testing 'Appliance' class and its methods")
            assertNotNull(applianceInstance)
            assertNotNull(washingMachineInstance)
            assertNotNull(refrigeratorInstance)
            assertNotNull(turnOnMethod)
            assertNotNull(turnOffMethod)
            assertNotNull(toStringMethod)

            val isOnField = applianceInstance.javaClass.getDeclaredField("isOn")
            isOnField.isAccessible = true

            println("Testing turnOff()")
            // Test turnOff method
            turnOffMethod.invoke(applianceInstance)
            assertEquals(false, isOnField.get(applianceInstance), "Expected 'isOn' to be false after turnOff().")

            println("Testing turnOn()")
            // Test turnOn method
            turnOnMethod.invoke(applianceInstance)
            assertEquals(true, isOnField.get(applianceInstance), "Expected 'isOn' to be true after turnOn().")

            println("Testing turnOff() doublecheck")
            // Test turnOff method
            turnOffMethod.invoke(applianceInstance)
            assertEquals(false, isOnField.get(applianceInstance), "Expected 'isOn' to be false after turnOff().")

            println("Testing toString()")
            // Test toString method
            val toStringOutput = toStringMethod.invoke(applianceInstance) as String
            println(toStringOutput)
            assertTrue(toStringOutput.contains("brand=GenericBrand"), "Expected output to contain 'brand=GenericBrand'.")
            assertTrue(toStringOutput.contains("powerConsumption=1000"), "Expected output to contain 'powerConsumption=1000'.")
            assertTrue(toStringOutput.contains("isOn=false"), "Expected output to contain 'isOn=false'.")

            val showFeaturesMethod = washingMachineClass.getMethod("showFeatures")
            println("Testing WashingMachine.showFeatures()")
            // Test WashingMachine showFeatures method
            val washingMachineFeaturesOutput = captureOutput {
                showFeaturesMethod.invoke(washingMachineInstance)
            }
            println(washingMachineFeaturesOutput)
            assertTrue(washingMachineFeaturesOutput.isNotBlank(), "Expected some output text for showFeatures().")

            val showFeaturesMethodFridge = refrigeratorClass.getMethod("showFeatures")
            println("Testing Refrigerator.showFeatures()")
            // Test Refrigerator showFeatures method
            val refrigeratorFeaturesOutput = captureOutput {
                showFeaturesMethodFridge.invoke(refrigeratorInstance)
            }
            println(refrigeratorFeaturesOutput)
            assertTrue(refrigeratorFeaturesOutput.isNotBlank(), "Expected some output text for showFeatures().")

        } catch (e: NoSuchMethodException) {
            println("Exercise 1 failed: Method not found ${e.message?.split("Part4InheritanceAndAccessModification\$")?.getOrNull(1) ?: e.message}")
            fail("Exercise 1 failed: Method not found ${e.message?.split("Part4InheritanceAndAccessModification\$")?.getOrNull(1) ?: e.message}")
        } catch (e: Exception) {
            println("Exercise 1 failed: ${e.message}")
            fail("Exercise 1 failed: ${e.message}")
        }
    }

    // ---------------------- EXERCISE 2
    @Test
    fun `exercise 02 testEmployeeClass`() {
        val employeeClass = try {
            clazz.declaredClasses.first { it.simpleName == "Employee" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (employeeClass == null) {
            println("Exercise 2 failed: 'Employee' class not found.")
            fail("Exercise 2 failed: 'Employee' class not found.")
        }

        val managerClass = try {
            clazz.declaredClasses.first { it.simpleName == "Manager" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (managerClass == null) {
            println("Exercise 2 failed: 'Manager' class not found.")
            fail("Exercise 2 failed: 'Manager' class not found.")
        }

        val developerClass = try {
            clazz.declaredClasses.first { it.simpleName == "Developer" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (developerClass == null) {
            println("Exercise 2 failed: 'Developer' class not found.")
            fail("Exercise 2 failed: 'Developer' class not found.")
        }

        val internClass = try {
            clazz.declaredClasses.first { it.simpleName == "Intern" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (internClass == null) {
            println("Exercise 2 failed: 'Intern' class not found.")
            fail("Exercise 2 failed: 'Intern' class not found.")
        }

        try {
            val employeeConstructor = employeeClass.getConstructor(String::class.java, String::class.java, Double::class.java)
            val employeeInstance = employeeConstructor.newInstance("John Doe", "Employee", 50000.0)
            val managerConstructor = managerClass.getConstructor(String::class.java, Double::class.java, String::class.java)
            val managerInstance = managerConstructor.newInstance("Alice", 90000.0, "Sales")
            val developerConstructor = developerClass.getConstructor(String::class.java, Double::class.java, String::class.java)
            val developerInstance = developerConstructor.newInstance("Bob", 80000.0, "Kotlin")
            val internConstructor = internClass.getConstructor(String::class.java, Double::class.java, String::class.java)
            val internInstance = internConstructor.newInstance("Charlie", 30000.0, "MIT")

            val workMethod = employeeClass.getMethod("work")

            println("Testing 'Employee' class and its methods")
            assertNotNull(employeeInstance)
            assertNotNull(managerInstance)
            assertNotNull(developerInstance)
            assertNotNull(internInstance)
            assertNotNull(workMethod)

            println("Checking subclass relationships")
            // Check if subclasses extend Employee
            assertTrue(employeeClass.isAssignableFrom(managerClass), "Expected Manager to be a subclass of Employee.")
            assertTrue(employeeClass.isAssignableFrom(developerClass), "Expected Developer to be a subclass of Employee.")
            assertTrue(employeeClass.isAssignableFrom(internClass), "Expected Intern to be a subclass of Employee.")

            println("Testing Employee.work()")
            // Test Employee work method
            val employeeWorkOutput = captureOutput {
                workMethod.invoke(employeeInstance)
            }
            println(employeeWorkOutput)
            assertTrue(employeeWorkOutput.isNotBlank(), "Expected some output text for Employee.work().")

            println("Testing Manager.work()")
            // Test Manager work method
            val managerWorkMethod = managerClass.getMethod("work")
            val managerWorkOutput = captureOutput {
                managerWorkMethod.invoke(managerInstance)
            }
            println(managerWorkOutput)
            assertTrue(managerWorkOutput.isNotBlank(), "Expected some output text for Manager.work().")

            println("Testing Developer.work()")
            // Test Developer work method
            val developerWorkMethod = developerClass.getMethod("work")
            val developerWorkOutput = captureOutput {
                developerWorkMethod.invoke(developerInstance)
            }
            println(developerWorkOutput)
            assertTrue(developerWorkOutput.isNotBlank(), "Expected some output text for Developer.work().")

            println("Testing Intern.work()")
            // Test Intern work method
            val internWorkMethod = internClass.getMethod("work")
            val internWorkOutput = captureOutput {
                internWorkMethod.invoke(internInstance)
            }
            println(internWorkOutput)
            assertTrue(internWorkOutput.isNotBlank(), "Expected some output text for Intern.work().")

        } catch (e: NoSuchMethodException) {
            println("Exercise 2 failed: Method not found ${e.message?.split("Employee")?.getOrNull(1) ?: e.message}")
            fail("Exercise 2 failed: Method not found ${e.message?.split("Employee")?.getOrNull(1) ?: e.message}")
        } catch (e: Exception) {
            println("Exercise 2 failed: ${e.message}")
            fail("Exercise 2 failed: ${e.message}")
        }
    }


    // ---------------------- EXERCISE 3
    @Test
    fun `exercise 03 testCourseClass`() {
        val courseClass = try {
            clazz.declaredClasses.first { it.simpleName == "Course" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (courseClass == null) {
            println("Exercise 3 failed: 'Course' class not found.")
            fail("Exercise 3 failed: 'Course' class not found.")
        }

        try {
            val courseConstructor = courseClass.getConstructor(String::class.java, String::class.java)
            val courseInstance = courseConstructor.newInstance("Mathematics", "Dr. Smith")

            val creditsField = try {
                courseClass.getDeclaredField("credits")
            } catch (e: NoSuchFieldException) {
                null
            }

            if (creditsField == null) {
                println("Exercise 3 failed: 'credits' field not found.")
                fail("Exercise 3 failed: 'credits' field not found.")
            }

            val courseDurationMethod = try {
                courseClass.getMethod("getCourseDuration")
            } catch (e: NoSuchMethodException) {
                null
            }

            if (courseDurationMethod == null) {
                println("Exercise 3 failed: 'courseDuration' method not found.")
                fail("Exercise 3 failed: 'courseDuration' method not found.")
            }

            val creditsSetter = courseClass.getMethod("setCredits", Int::class.java)

            println("Testing 'Course' class and its properties")
            assertNotNull(courseInstance)
            assertNotNull(creditsField)
            assertNotNull(creditsSetter)
            assertNotNull(courseDurationMethod)

            println("Testing initial credits property")
            // Test initial value of credits property
            creditsField.isAccessible = true
            assertEquals(1, creditsField.get(courseInstance), "Expected initial credits to be 1.")

            println("Testing custom setter for credits property with valid value")
            // Test custom setter with valid value
            creditsSetter.invoke(courseInstance, 3)
            assertEquals(3, creditsField.get(courseInstance), "Expected credits to be 3 after setting a valid value.")

            println("Testing custom setter for credits property with invalid value")
            // Test custom setter with invalid value (no exception should be thrown)
            creditsSetter.invoke(courseInstance, 6)
            assertEquals(3, creditsField.get(courseInstance), "Expected credits to remain 3 after setting an invalid value.")

            println("Testing custom getter for courseDuration property")
            // Test custom getter for courseDuration property
            val courseDuration = courseDurationMethod.invoke(courseInstance) as Int
            assertEquals(45, courseDuration, "Expected courseDuration to be 45 hours (3 credits * 15 hours).")

        } catch (e: NoSuchMethodException) {
            println("Exercise 3 failed: Method not found ${e.message?.split("Course")?.getOrNull(1) ?: e.message}")
            fail("Exercise 3 failed: Method not found ${e.message?.split("Course")?.getOrNull(1) ?: e.message}")
        } catch (e: Exception) {
            println("Exercise 3 failed: ${e.message}")
            fail("Exercise 3 failed: ${e.message}")
        }
    }


    // ---------------------- EXERCISE 4
    @Test
    fun `exercise 04 testAthleteClass`() {
        val athleteClass = try {
            clazz.declaredClasses.first { it.simpleName == "Athlete" }
        } catch (e: NoSuchElementException) {
            null
        }

        if (athleteClass == null) {
            println("Exercise 4 failed: 'Athlete' class not found.")
            fail("Exercise 4 failed: 'Athlete' class not found.")
        }

        try {
            val athleteConstructor = athleteClass.getConstructor(String::class.java, String::class.java)
            val athleteInstance = athleteConstructor.newInstance("A123", "Jane Doe")

            println("Testing 'Athlete' class and its properties")
            assertNotNull(athleteInstance)

            println("Testing calculateFitnessLevel() private method")
            // Accessing the private calculateFitnessLevel method using reflection
            val calculateFitnessLevelMethod = athleteClass.getDeclaredMethod("calculateFitnessLevel")
            assertTrue(Modifier.isPrivate(calculateFitnessLevelMethod.modifiers), "Expected calculateFitnessLevel() to be private.")
            calculateFitnessLevelMethod.isAccessible = true
            val fitnessLevel = calculateFitnessLevelMethod.invoke(athleteInstance) as Int
            assertTrue(fitnessLevel in 0..100, "Expected fitness level to be between 0 and 100.")

            println("Testing fitnessLevel property")
            // Testing the fitnessLevel property
            val fitnessLevelField = athleteClass.getDeclaredField("fitnessLevel")
            fitnessLevelField.isAccessible = true
            val fitnessLevelValue = fitnessLevelField.get(athleteInstance) as Int
            assertTrue(fitnessLevelValue in 0..100, "Expected fitness level to be between 0 and 100.")

        } catch (e: NoSuchMethodException) {
            println("Exercise 4 failed: Method not found ${e.message?.split("Athlete")?.getOrNull(1) ?: e.message}")
            fail("Exercise 4 failed: Method not found ${e.message?.split("Athlete")?.getOrNull(1) ?: e.message}")
        } catch (e: Exception) {
            println("Exercise 4 failed: ${e.message}")
            fail("Exercise 4 failed: ${e.message}")
        }
    }
}