package com.project.LibraryManagement.DataAccessLayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM User u WHERE u.name=:name", nativeQuery = true)
    public List<User> getUsersByUsername(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "SELECT * FROM User u WHERE u.mobile=:mobile", nativeQuery = true)
    public List<User> getUsersByMobile(@Param("mobile") String mobile);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM User u WHERE u.name=:name", nativeQuery = true)
    public void deleteUserByUsername(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE User u SET u.email=:email WHERE u.name=:name", nativeQuery = true)
    public void updateUserEmailByUsername(@Param("name") String name, @Param("email") String email);

    @Transactional
    @Query(value = "SELECT * FROM User u WHERE u.name=:name", nativeQuery = true)
    public User searchUserByUsername(@Param("name") String name);
}
