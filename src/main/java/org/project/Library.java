package org.project;

import org.project.exceptions.NonUniqueIsbn;

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

        if(availableBooks.containsKey(isbn)){
            throw new NonUniqueIsbn("Book with ISBN: "+isbn+" is already there");
        }
        availableBooks.put(isbn, book);
    }
}
