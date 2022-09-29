package edu.javacourse.studentorder.exception;

public class TransportException extends Exception{
    TransportException(){}
    public TransportException(String message) {
        super(message);
    }

    public TransportException(String message, Throwable cause) {
        super(message, cause);
    }
}
