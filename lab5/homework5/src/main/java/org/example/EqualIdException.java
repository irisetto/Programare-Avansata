package org.example;

public class EqualIdException extends Exception {
    public EqualIdException() {
        super("This id exists already!");
    }
}
