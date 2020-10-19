package com.project.LibraryManagement.DataAccessLayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IssuedBookRepository extends JpaRepository<IssuedBooks, Integer> {
    @Transactional
    @Query(value = "SELECT * FROM issued_books ib WHERE ib.user_id=:user_id", nativeQuery = true)
    public IssuedBooks searchIssuedBooksByUserId(@Param("user_id") int user_id);
}
