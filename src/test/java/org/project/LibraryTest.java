package org.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.exceptions.BookNotFoundException;
import org.project.exceptions.NoBooksAvailableException;
import org.project.exceptions.NonUniqueIsbnException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class LibraryTest {

    Library library;

    @BeforeEach
    void setup(){
        library = new Library();
    }

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

    @Test
    public void testReturnBookIfBorrowed(){
        Book book1 = new Book("1234", "PranavBook", "Pranav", 2021);
        Book book2 = new Book("1235", "PranavBook", "Pranav", 2021);
        Book book3 = new Book("4321", "PratikBook", "Pratik", 2022);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        String book1Title = "PratikBook";
        String book2Title = "PranavBook";
        library.borrowBook(book1Title);
        library.borrowBook(book2Title);

        String returnIsbn = "4321";
        library.returnBook(returnIsbn);

        int expectedAvailableBooks = 2;
        int actualAvailableBooks = library.getTotalAvailableBooks();

        int expectedBorrowedBooks = 1;
        int actualBorrowedBooks = library.getTotalBorrowedBooks();

        assertEquals(expectedAvailableBooks, actualAvailableBooks);
        assertEquals(expectedBorrowedBooks, actualBorrowedBooks);

    }

    @Test
    public void testReturnBookIfNotBorrowed(){
        Book book1 = new Book("1234", "PranavBook", "Pranav", 2021);
        Book book2 = new Book("4321", "PratikBook", "Pratik", 2022);

        library.addBook(book1);
        library.addBook(book2);

        String bookTitle = "PranavBook";
        library.borrowBook(bookTitle);

        Exception exception = assertThrows(BookNotFoundException.class, ()->{
            String returnIsbn = "5678";
            library.returnBook(returnIsbn);
        });
        assertTrue(exception.getMessage().contains("Book with ISBN: 5678 is not borrowed. You can add this as a new book"));
    }

    @Test
    public void testViewAvailableBooksIfAvailable(){
        Book book1 = new Book("1234", "PranavBook", "Pranav", 2021);
        Book book2 = new Book("1235", "PranavBook", "Pranav", 2021);
        Book book3 = new Book("4321", "PratikBook", "Pratik", 2022);
        Book book4 = new Book("5678", "VedBook", "Ved", 2022);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);

        String book1Title = "PratikBook";
        String book2Title = "PranavBook";
        library.borrowBook(book1Title);
        library.borrowBook(book2Title);

        Set<Book> expectedAvailableBooks = new HashSet<>();
        expectedAvailableBooks.add(book2);
        expectedAvailableBooks.add(book4);

        Set<Book> actualAvailableBooks = library.viewAvailableBooks();

        assertEquals(expectedAvailableBooks, actualAvailableBooks);
    }

    @Test
    public void testViewAvailableBooksIfNotAvailable(){
        Exception exception = assertThrows(NoBooksAvailableException.class, ()->{
            library.viewAvailableBooks();
        });
        assertTrue(exception.getMessage().contains("There is no book available right now. You can add a new book."));
    }

    @Test
    public void testCreateBookWithInvalidIsbn(){
        Exception emptyIsbn = assertThrows(IllegalArgumentException.class, ()->{
            new Book("", "PranavBook", "Pranav", 2021);
        });

        Exception spaceIsbn = assertThrows(IllegalArgumentException.class, ()->{
            new Book(" ", "PranavBook", "Pranav", 2021);
        });

        Exception nullIsbn = assertThrows(IllegalArgumentException.class, ()->{
            new Book(null, "PranavBook", "Pranav", 2021);
        });


        assertTrue(spaceIsbn.getMessage().contains("ISBN cannot be null or empty."));
        assertTrue(emptyIsbn.getMessage().contains("ISBN cannot be null or empty."));
        assertTrue(nullIsbn.getMessage().contains("ISBN cannot be null or empty."));
    }

    @Test
    public void testCreateBookWithInvalidTitle(){
        Exception emptyTitle = assertThrows(IllegalArgumentException.class, ()->{
            new Book("1234", "", "Pranav", 2021);
        });

        Exception spaceTitle = assertThrows(IllegalArgumentException.class, ()->{
            new Book("1234", " ", "Pranav", 2021);
        });

        Exception nullTitle = assertThrows(IllegalArgumentException.class, ()->{
            new Book("1234", null, "Pranav", 2021);
        });


        assertTrue(spaceTitle.getMessage().contains("Title cannot be null or empty."));
        assertTrue(emptyTitle.getMessage().contains("Title cannot be null or empty."));
        assertTrue(nullTitle.getMessage().contains("Title cannot be null or empty."));
    }

    @Test
    public void testCreateBookWithInvalidAuthor(){
        Exception emptyAuthor = assertThrows(IllegalArgumentException.class, ()->{
            new Book("1234", "PranavBook", "", 2021);
        });

        Exception spaceAuthor = assertThrows(IllegalArgumentException.class, ()->{
            new Book("1234", "PranavBook", " ", 2021);
        });

        Exception nullAuthor = assertThrows(IllegalArgumentException.class, ()->{
            new Book("1234", "PranavBook", null, 2021);
        });


        assertTrue(spaceAuthor.getMessage().contains("Author cannot be null or empty."));
        assertTrue(emptyAuthor.getMessage().contains("Author cannot be null or empty."));
        assertTrue(nullAuthor.getMessage().contains("Author cannot be null or empty."));
    }

    @Test
    public void testCreateBookWithInvalidPublicationYear(){
        Exception zeroYear = assertThrows(IllegalArgumentException.class, ()->{
            new Book("1234", "PranavBook", "Pranav", 0);
        });

        Exception negativeYear = assertThrows(IllegalArgumentException.class, ()->{
            new Book("1234", "PranavBook", "Pranav", -5);
        });

        Exception futureYear = assertThrows(IllegalArgumentException.class, ()->{
            new Book("1234", "PranavBook", "Pranav", 2030);
        });

        assertTrue(zeroYear.getMessage().contains("Given year is invalid."));
        assertTrue(negativeYear.getMessage().contains("Given year is invalid."));
        assertTrue(futureYear.getMessage().contains("Given year is invalid."));
    }

}
