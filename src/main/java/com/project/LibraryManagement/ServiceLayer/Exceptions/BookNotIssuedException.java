package com.project.LibraryManagement.ServiceLayer.Exceptions;

public class BookNotIssuedException extends RuntimeException {
    public BookNotIssuedException() {
        super("Book not issued!");
    }
}