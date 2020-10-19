package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.DataAccessLayer.IssuedBookRepository;
import com.project.LibraryManagement.DataAccessLayer.IssuedBooks;
import com.project.LibraryManagement.ServiceLayer.Exceptions.BookNotIssuedException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class IssuedBooksController {
    @Autowired
    IssuedBookRepository issuedBookRepository;

    // uri : http://localhost:8080/issuedBooksAll
    @ApiOperation("This API will show all issued-books from library database's issuedBooks table.")
    @GetMapping("/issuedBooksAll")
    List<IssuedBooks> findAllIssuedBooks() {
        return issuedBookRepository.findAll();
    }

    // uri : http://localhost:8080/issueBooks
    @ApiOperation("This API will issue a book in library database's issuedBooks table.")
    @PostMapping("/issueBooks")
    @ResponseStatus(HttpStatus.CREATED)
    public IssuedBooks issueBook(@RequestBody IssuedBooks issuedBook) {
        return issuedBookRepository.save(issuedBook);
    }

    // uri : http://localhost:8080/searchIssuedBooksByUserId?user_id=3
    @ApiOperation("This API will search for issued-books by username from library database's " +
            "issuedBooks table")
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("/searchIssuedBooksByUserId")
    public IssuedBooks searchIssuedBooksByUserId(@RequestParam("user_id") int user_id) {
        return Optional.ofNullable(issuedBookRepository.searchIssuedBooksByUserId(user_id))
                .orElseThrow(() -> new BookNotIssuedException());
    }
}