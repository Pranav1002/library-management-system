package org.project;

import org.junit.jupiter.api.Test;
import org.project.exceptions.BookNotFoundException;
import org.project.exceptions.NonUniqueIsbnException;

import static org.junit.jupiter.api.Assertions.*;


public class LibraryTest {

    Library library = new Library();

    @Test
    public void testZeroBookAvailable(){

        int expected = 0;
        int actual = library.getTotalAvailableBooks();

        assertEquals(expected, actual);
    }

    @Test
    public void testAdd1Book(){

        Book book = new Book("1234", "MyBook", "Pranav", 2021);
        library.addBook(book);

        int expected = 1;
        int actual = library.getTotalAvailableBooks();

        assertEquals(expected, actual);
    }

    @Test
    public void testAdd2BooksWithUniqueIsbn(){

        Book book1 = new Book("1234", "MyBook", "Pranav", 2021);
        Book book2 = new Book("1235", "MyBook", "Pranav", 2021);
        library.addBook(book1);
        library.addBook(book2);

        int expected = 2;
        int actual = library.getTotalAvailableBooks();

        assertEquals(expected, actual);
    }

    @Test
    public void testAdd2BooksWithSameIsbn(){

        Book book1 = new Book("1234", "MyBook", "Pranav", 2021);
        Book book2 = new Book("1234", "MyBook", "Pranav", 2021);

        library.addBook(book1);

        Exception exception = assertThrows(NonUniqueIsbnException.class, ()->{
            library.addBook(book2);
        });
        assertTrue(exception.getMessage().contains("Book with ISBN: 1234 is already there"));
    }

    @Test
    public void testFindBookByTitleIfAvailable(){

        Book book1 = new Book("1234", "MyBook", "Pranav", 2021);
        Book book2 = new Book("1235", "MyBook", "Pranav", 2021);
        library.addBook(book1);
        library.addBook(book2);

        String checkTitle = "MyBook";

        String actualIsbn = library.findBookByTitle(checkTitle);
        String expectedIsbn = "1234";

        assertEquals(expectedIsbn, actualIsbn);
    }

    @Test
    public void testFindBookByTitleIfNotAvailable(){

        Book book1 = new Book("1234", "MyBook", "Pranav", 2021);
        library.addBook(book1);

        String checkTitle = "HelloBook";

        String actualIsbn = library.findBookByTitle(checkTitle);
        assertNull(actualIsbn);
    }

    @Test
    public void testZeroBookBorrowed(){
        Book book = new Book("1234", "MyBook", "Pranav", 2021);
        library.addBook(book);

        int expectedValue = 0;
        int actualValue = library.getTotalBorrowedBooks();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testBorrowBookIfAvailable(){
        Book book1 = new Book("1234", "PranavBook", "Pranav", 2021);
        Book book2 = new Book("1235", "PranavBook", "Pranav", 2021);
        Book book3 = new Book("4321", "PratikBook", "Pratik", 2022);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        String bookTitle = "PranavBook";

        library.borrowBook(bookTitle);

        int expectedAvailableBooks = 2;
        int actualAvailableBooks = library.getTotalAvailableBooks();

        int expectedBorrowedBooks = 1;
        int actualBorrowedBooks = library.getTotalBorrowedBooks();

        assertEquals(expectedAvailableBooks, actualAvailableBooks);
        assertEquals(expectedBorrowedBooks, actualBorrowedBooks);
    }

    @Test
    public void testBorrowBookIfNotAvailable(){
        Book book1 = new Book("1234", "PranavBook", "Pranav", 2021);

        String bookTitle = "PratikBook";

        Exception exception = assertThrows(BookNotFoundException.class, ()->{
            library.borrowBook(bookTitle);
        });
        assertTrue(exception.getMessage().contains("Book with given title is not available right now"));

    }

}
