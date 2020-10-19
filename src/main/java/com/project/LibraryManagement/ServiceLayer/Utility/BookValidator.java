package com.project.LibraryManagement.ServiceLayer.Utility;

import com.project.LibraryManagement.DataAccessLayer.Book;
import org.springframework.stereotype.Component;

@Component
public class BookValidator {
    public boolean isValid(Book book) {
        if (book.getTitle() == "" || book.getTitle() == null)
            return false;
        return true;
    }
}
