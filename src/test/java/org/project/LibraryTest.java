package org.project;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LibraryTest {

    Library library = new Library();

    @Test
    public void testZeroBookAvailable(){
        assertEquals(0, library.getTotalBooks());
    }
}
