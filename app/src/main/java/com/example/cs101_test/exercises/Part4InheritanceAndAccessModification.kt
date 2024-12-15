package com.example.cs101_test.exercises

object Part4InheritanceAndAccessModification {
    // ---------------------- EXERCISE 1
    // Create a base/open class called Appliance with 3 properties: brand, powerConsumption, and isOn
    // Add a method: turnOn() that sets isOn to true
    // Add another method: turnOff() that sets isOn to false
    // Override the toString method such that if an appliance object is printed, it will return a string in the following format:
    //      [brand=brand, powerConsumption=powerConsumption, isOn=isOn]
    // Create 2 classes (WashingMachine and Refrigerator) that extend the Appliance class
    //      and add a method to show/print their unique features e.g. has a quick wash feature (showFeatures)


    // ---------------------- EXERCISE 2
    // Create a base class called Employee with properties: name, position, and salary
    // Derive three classes Manager, Developer, and Intern from Employee
    // Implement the following extra properties: department for Manager, programmingLanguage for Developer, school for Intern
    // Implement the method work() for all classes, which prints a message indicating the type of work the employee is doing
    // E.g. an Employee.work() should print one thing and Developer.work() another


    // ---------------------- EXERCISE 3
    // Create a Kotlin class called Library with the following properties: name, location
    // Create a variable called books (list of strings)
    // Implement custom getter and setter methods for the books property.
    // The setter should ensure that the book list is always non-empty.
    // If an empty list is attempted to be set, it should be set to a default list with a single book: "Default Book"


    // ---------------------- EXERCISE 4
    // Create a class named Course to represent course information
    // Include the properties courseName and instructor
    // Create a variable called credits
    // Implement a custom setter for the credits property to ensure that the credits can only be set if they are within the valid range (1 to 5)
    // Implement a custom getter for the courseDuration property to calculate the course duration based on the number of credits
    //      Assume that each credit equals 15 hours when calculating courseDuration


    // ---------------------- EXERCISE 5
    // Create a class Athlete with properties: id, name
    // Implement a private function to calculate the athlete's fitness level (calculateFitnessLevel)
    // To make calculateFitnessLevel easy to implement, just make it return an Int between 0 and 100 - you choose how to calculate it!
    // The class should have a public field fitnessLevel which uses the private function to return a result.
    // The setter for fitnessLevel should be private


}
