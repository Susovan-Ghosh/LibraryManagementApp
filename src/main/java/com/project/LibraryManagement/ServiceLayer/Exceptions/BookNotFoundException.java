package com.project.LibraryManagement.ServiceLayer.Exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("Book not found!");
    }
}
