package org.project.exceptions;

public class NonUniqueIsbnException extends RuntimeException{

    public NonUniqueIsbnException(String message){
        super(message);
    }

}
