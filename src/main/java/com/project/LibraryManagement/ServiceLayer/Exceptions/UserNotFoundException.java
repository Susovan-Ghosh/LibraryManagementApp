package com.project.LibraryManagement.ServiceLayer.Exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User not found!");
    }
}
