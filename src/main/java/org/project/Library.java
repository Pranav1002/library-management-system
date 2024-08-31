package org.project;

import org.project.exceptions.BookNotFoundException;
import org.project.exceptions.NonUniqueIsbnException;

import java.util.LinkedHashMap;
import java.util.Map;

public class Library {

    private Map<String, Book> availableBooks = new LinkedHashMap<>();
    private Map<String, Book> borrowedBooks = new LinkedHashMap<>();

    public int getTotalAvailableBooks(){
        int totalAvailableBooks = availableBooks.size();
        return totalAvailableBooks;
    }

    public int getTotalBorrowedBooks(){
        int totalBorrowedBooks = borrowedBooks.size();
        return totalBorrowedBooks;
    }

    public void addBook(Book book) {

        String isbn = book.getIsbn();

        boolean isDuplicate = availableBooks.containsKey(isbn);

        if(isDuplicate){
            throw new NonUniqueIsbnException("Book with ISBN: "+isbn+" is already there");
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

    public void borrowBook(String bookTitle) {

        String isbn = findBookByTitle(bookTitle);
        if(isbn!=null){
            Book book = availableBooks.get(isbn);

            borrowedBooks.put(isbn, book);
            availableBooks.remove(isbn);
        }
        else{
            throw new BookNotFoundException("Book with given title is not available right now");
        }

    }

    public void returnBook(String isbn) {

        Book book = borrowedBooks.get(isbn);
        borrowedBooks.remove(isbn);
        availableBooks.put(isbn, book);
    }
}
