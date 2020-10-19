package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.DataAccessLayer.User;
import com.project.LibraryManagement.DataAccessLayer.UserRepository;
import com.project.LibraryManagement.ServiceLayer.Exceptions.UserNotFoundException;
import com.project.LibraryManagement.ServiceLayer.Utility.UserValidator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserValidator userValidator;

    // uri : http://localhost:8080/users/create
    @ApiOperation("This API will create a user entry in library database's user table.")
    @PostMapping("/users/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody User user) {
        if (userValidator.isValid(user))
            userRepository.save(user);
    }

    // uri : http://localhost:8080/users/getByUsername?name=Susovan Ghosh
    @ApiOperation("This API will fetch users by user name from library database's user table")
    @GetMapping("/users/getByUsername")
    @ResponseStatus(HttpStatus.FOUND)
    public List<User> getUsersByUsername(@RequestParam("name") String name) {
        return userRepository.getUsersByUsername(name);
    }

    // uri : http://localhost:8080/users/getByMobile?mobile=7003047536
    @ApiOperation("This API will fetch users by mobile number from library database's user table")
    @GetMapping("/users/getByMobile")
    @ResponseStatus(HttpStatus.FOUND)
    public List<User> getUsersByMobile(@RequestParam("mobile") String mobile) {
        return userRepository.getUsersByMobile(mobile);
    }

    // uri : http://localhost:8080/users/deleteByUsername?name=Susovan Ghosh
    @ApiOperation("This API will delete an user by username from library database's user table")
    @DeleteMapping("/users/deleteByUsername")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserByUsername(@RequestParam("name") String name) {
        userRepository.deleteUserByUsername(name);
    }

    // uri : http://localhost:8080/users/updateEmailByUsername/Susovan Ghosh
    @ApiOperation("This API will update email by username in library database's user table")
    @PatchMapping("/users/updateEmailByUsername/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserEmailByUsername(@PathVariable("name") String name,
                                          @RequestBody String email) {
        userRepository.updateUserEmailByUsername(name, email);
    }

    // uri : http://localhost:8080/users/searchByUsername?name=Susovan Ghosh
    @ApiOperation("This API will search an user by username from library database's user table")
    @GetMapping("/users/searchByUsername")
    @ResponseStatus(HttpStatus.FOUND)
    public User searchUserByUsername(@RequestParam("name") String name) {
        return Optional.ofNullable(userRepository.searchUserByUsername(name))
                .orElseThrow(() -> new UserNotFoundException());
    }
}
