package org.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LibraryTest {

    Library library = new Library();

    @Test
    public void testZeroBookAvailable(){
        int expected = 0;
        int actual = library.getTotalBooks();
        assertEquals(expected, actual);
    }

    @Test
    public void testAdd1Book(){
        Book book = new Book("1234", "MyBook", "Pranav", 2021);
        library.addBook(book);
        int expected = 1;
        int actual = library.getTotalBooks();
        assertEquals(expected, actual);
    }
}
