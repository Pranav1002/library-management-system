package org.project;

import java.util.HashMap;
import java.util.Map;

public class Library {

    Map<String, Book> availableBooks = new HashMap<>();

    public int getTotalBooks(){
        int totalBooks = availableBooks.size();
        return totalBooks;
    }

    public void addBook(Book book) {

        String isbn = book.getIsbn();
        availableBooks.put(isbn, book);
    }
}
