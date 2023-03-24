package ru.beerwaroff.exceptions;

public class NonExistentArgument extends Exception{
    String message;
    public NonExistentArgument(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
