package com.project.LibraryManagement.DataAccessLayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM Book b WHERE b.author=:author", nativeQuery = true)
    public List<Book> getBooksByAuthor(@Param("author") String author);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM Book b WHERE b.publisher=:publisher", nativeQuery = true)
    public List<Book> getBooksByPublisher(@Param("publisher") String publisher);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM Book b WHERE b.country=:country", nativeQuery = true)
    public List<Book> getBooksByCountry(@Param("country") String country);

    @Transactional
    @Query(value = "SELECT * FROM Book b WHERE b.title=:title", nativeQuery = true)
    public Book searchBookByTitle(@Param("title") String title);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM Book b WHERE b.title=:title", nativeQuery = true)
    public void deleteBookByTitle(@Param("title") String title);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Book b SET b.title=:title WHERE b.author=:author", nativeQuery = true)
    public void updateBookTitleByAuthor(@Param("author") String author, @Param("title") String title);
}