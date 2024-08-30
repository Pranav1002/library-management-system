package org.project;

import java.util.HashMap;
import java.util.Map;

public class Library {

    Map<String, Book> availableBooks = new HashMap<>();

    public int getTotalBooks(){
        return availableBooks.size();
    }
}
