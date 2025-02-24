package com.example.cs101_test.exercises

import android.provider.MediaStore.Audio.Media

object Part5InterfacesAndAbstractClasses {

    // ---------------------- EXERCISE 1
    // Create an abstract class called MediaContent with 4 properties: title, releaseYear, genre, and rating
    // Add an abstract method: playContent() that needs to be implemented by subclasses
    // Create 3 classes (Movie, Series, and Documentary) that extend the MediaContent class
    //   - Movie class should have an additional property: director
    //   - Series class should have an additional property: seasons
    //   - Documentary class should have an additional property: subject
    // Each subclass should override the playContent() method to print a message specific to its type
    abstract class Mediacontent(
        var title: String,
        val releaseYear: Int,
        val genre: String,
        var rating: Float
    ) {
        abstract fun playContent();
    }

    class Movie(
        title: String,
        releaseYear: Int,
        genre: String,
        rating: Float,
        val director: String
    ): Mediacontent(title, releaseYear, genre, rating) {
        override fun playContent() {
            println("Movie time :3");
        }
    }

    class Series(
        title: String,
        releaseYear: Int,
        genre: String,
        rating: Float,
        val seasons: Int
    ): Mediacontent(title, releaseYear, genre, rating) {
        override fun playContent() {
            println("Binge time!");
        }
    }

    class Documentary(
        title: String,
        releaseYear: Int,
        genre: String,
        rating: Float,
        val subject: String
    ): Mediacontent(title, releaseYear, genre, rating) {
        override fun playContent() {
            println("Time to learn :)");
        }
    }


    // ---------------------- EXERCISE 2
    // Create an interface called Account with 3 properties: accountNumber, accountHolder, and balance (Double)
    // Add methods: deposit(amount: Double), withdraw(amount: Double), and checkBalance() that need to be implemented by classes
    //      checkBalance should return a Double
    // Create 3 classes (SavingsAccount, CurrentAccount, and BusinessAccount) that implement the Account interface
    //   - SavingsAccount should have an additional property: interestRate
    //   - CurrentAccount should have an additional property: overdraftLimit
    //   - BusinessAccount should have an additional property: businessName
    // Each class should implement the methods to manage deposits and withdrawals with specific rules for each account type
    //      This means that for the CurrentAccount class, the withdraw function will be successful as long as
    //          the amount is less than or equal to the sum of the balance and overdraftLimit
    //      Should you try to withdraw more money than allowed, it should print: "Insufficient funds"
    interface Account{
        val accountNumber: Long;
        val accountHolder: String;
        var balance: Double;

        fun deposit(amount: Double);
        fun withdraw(amount: Double);
        fun checkBalance(): Double;
    }

    class SavingsAccount(
        override val accountNumber: Long,
        override val accountHolder: String,
        override var balance: Double,
        var interestRate: Float
    ): Account {
        override fun deposit(amount: Double) {
            balance += amount;
        }

        override fun withdraw(amount: Double) {
            balance -= amount;
        }

        override fun checkBalance(): Double {
            return balance;
        }
    }

    class CurrentAccount(
        override val accountNumber: Long,
        override val accountHolder: String,
        override var balance: Double,
        var overdraftLimit: Float
    ): Account {
        override fun deposit(amount: Double) {
            balance += amount;
        }

        override fun withdraw(amount: Double) {
            require(balance + overdraftLimit >= amount) { "Insufficient funds" }
            balance -= amount;
        }

        override fun checkBalance(): Double {
            return balance;
        }
    }

    class BusinessAccount(
        override val accountNumber: Long,
        override val accountHolder: String,
        override var balance: Double,
        var businessName: String
    ): Account {
        override fun deposit(amount: Double) {
            balance += amount;
        }

        override fun withdraw(amount: Double) {
            balance -= amount;
        }

        override fun checkBalance(): Double {
            return balance;
        }
    }

}
