# Kata: Library Management System - Incubyte Assignment

The Library Management System is a Java-based application designed to manage the borrowing and returning of books. This System is implemented using TDD(Test Driven Development) Approach.

## Implementation flow:

1. Initalized the project with Junit maven dependency
2. Created Book class
3. Created Library and LibraryTest claasses.
4. Wrote a test case for single featur at a time
5. Test case failed
6. Implemented the logic or solved the issues
7. Re-run the test case
8. Test case passed
9. Committed that test case and implementation on git
10. Repeated above steps(4 to 9) for all features
11. All Test case passed
12. Committed on git and pushed on GitHub

## Features

- Add new books to the library.
- Borrow books by searching for the title.
- Return borrowed books.
- View all available books.
- Handle various exceptions like non-unique ISBN, book not found, and no books available.

## Prerequisites

To run this project, you need to have the following software installed:

- [Java Development Kit (JDK) 11 or higher](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Apache Maven](https://maven.apache.org/install.html)

## Installation

   Open a terminal and run the following command to clone the repository:

1. **Clone the Repository:**
   ```
   git clone https://github.com/Pranav1002/library-management-system.git
   ```
  
2. **Navigate to the Project Directory:**
   ```
   cd library-management-system
   ```

3. **Build the Project:**
   ```
   mvn clean install
   ```

## Running Tests

This project includes a suite of unit tests to verify the functionality of the Library Management System.

- Run tests using Maven:
  ```
  mvn test
  ```
- This will run all the tests present in 'src/test/java' directory.

## Project Structure

library-management-system/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── org/
│   │           └── project/
│   │               ├── Book.java
│   │               ├── Library.java
│   │               └── exceptions/
│   │                   ├── BookNotFoundException.java
│   │                   ├── NoBooksAvailableException.java
│   │                   └── NonUniqueIsbnException.java
│   │              
│   │
│   └── test/
│       └── java/
│           └── org/
│               └── project/
│                   └── LibraryTest.java
│
├── pom.xml
└── README.md


## Thank you 
