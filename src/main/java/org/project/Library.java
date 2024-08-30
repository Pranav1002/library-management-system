package org.project;

import org.project.exceptions.NonUniqueIsbn;

import java.util.LinkedHashMap;
import java.util.Map;

public class Library {

    Map<String, Book> availableBooks = new LinkedHashMap<>();
    Map<String, Book> borrowedBooks = new LinkedHashMap<>();

    public int getTotalBooks(){
        int totalBooks = availableBooks.size();
        return totalBooks;
    }

    public void addBook(Book book) {

        String isbn = book.getIsbn();

        boolean isDuplicate = availableBooks.containsKey(isbn);

        if(isDuplicate){
            throw new NonUniqueIsbn("Book with ISBN: "+isbn+" is already there");
        }
        availableBooks.put(isbn, book);
    }


    public String findBookByTitle(String title) {

        for(Book book: availableBooks.values()){
            String bookTitle = book.getTitle();
            if(bookTitle.equals(title)){
                String isbn = book.getIsbn();
                return isbn;
            }
        }
        return null;
    }
}
