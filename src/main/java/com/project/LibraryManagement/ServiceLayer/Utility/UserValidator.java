package com.project.LibraryManagement.ServiceLayer.Utility;

import com.project.LibraryManagement.DataAccessLayer.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    public boolean isValid(User user) {
        if (user.getName() == "" || user.getName() == null)
            return false;
        return true;
    }
}
