package com.project.LibraryManagement.Controller;

import com.project.LibraryManagement.DataAccessLayer.Book;
import com.project.LibraryManagement.DataAccessLayer.BookRepository;
import com.project.LibraryManagement.ServiceLayer.Exceptions.BookNotFoundException;
import com.project.LibraryManagement.ServiceLayer.Utility.BookValidator;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookValidator bookValidator;

    // uri : http://localhost:8080/books/create
    @ApiOperation("This API will create a book entry in library database's book table.")
    @PostMapping("/books/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody Book book) {
        if (bookValidator.isValid(book))
            bookRepository.save(book);
    }

    // uri : http://localhost:8080//books/getByAuthor?author=JK Rowling
    @ApiOperation("This API will fetch books by author name from library database's book table")
    @GetMapping("/books/getByAuthor")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Book> getBooksByAuthor(@RequestParam("author") String author) {
        return bookRepository.getBooksByAuthor(author);
    }

    // uri : http://localhost:8080//books/getByPublisher?publisher=Scholastic Corporation
    @ApiOperation("This API will fetch books by publisher name from library database's book table")
    @GetMapping("/books/getByPublisher")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Book> getBooksByPublisher(@RequestParam("publisher") String publisher) {
        return bookRepository.getBooksByPublisher(publisher);
    }

    // uri : http://localhost:8080//books/getByCountry?country=United Kingdom
    @ApiOperation("This API will fetch books by country name from library database's book table")
    @GetMapping("/books/getByCountry")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Book> getBooksByCountry(@RequestParam("country") String country) {
        return bookRepository.getBooksByCountry(country);
    }

    // uri : http://localhost:8080/books/deleteByTitle?title=Harry Potter
    @ApiOperation("This API will delete a book by title from library database's book table")
    @DeleteMapping("/books/deleteByTitle")
    @ResponseStatus(HttpStatus.OK)
    public void deleteBookByTitle(@RequestParam("title") String title) {
        bookRepository.deleteBookByTitle(title);
    }

    // uri : http://localhost:8080/books/updateTitleByAuthor/JK Rowling
    @ApiOperation("This API will update book title by author name in library database's book table.")
    @PatchMapping("/books/updateTitleByAuthor/{author}")
    @ResponseStatus(HttpStatus.OK)
    public void updateBookTitleByAuthor(@PathVariable("author") String author,
                                        @RequestBody String title) {
        bookRepository.updateBookTitleByAuthor(author, title);
    }

    // uri : http://localhost:8080/books/searchBookByTitle?title=Harry Potter
    @ApiOperation("This API will search a book by title from library database's book table")
    @GetMapping("/books/searchBookByTitle")
    @ResponseStatus(HttpStatus.FOUND)
    public Book searchBookByTitle(@RequestParam("title") String title) {
        return Optional.ofNullable(bookRepository.searchBookByTitle(title))
                .orElseThrow(() -> new BookNotFoundException());
    }
}