package org.project.exceptions;

public class NoBooksAvailableException extends RuntimeException{
    public NoBooksAvailableException(){
        super("There is no book available right now. You can add a new book.");
    }
}
