package org.project.exceptions;

public class NonUniqueIsbn extends RuntimeException{

    public NonUniqueIsbn(String message){
        super(message);
    }

}
