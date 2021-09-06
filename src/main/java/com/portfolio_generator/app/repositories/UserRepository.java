package com.portfolio_generator.app.repositories;

import com.portfolio_generator.app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User findUserByUsername(@Param("username") String username);

    User findUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.lastName = ?1")
    User findUserByLastName(@Param("lastName") String lastName);

    @Query("SELECT u FROM User  u WHERE u.username = ?1 AND u.lastName = ?1")
    User findUserByUsernameAndLastName(@Param("username") String username, @Param("lastName") String lastName);

    @Query("SELECT u FROM User u WHERE u.roles = ?1")
    User findUserByRoles(@Param("roles") String roles);

    List<User> findByUsernameContainsOrFirstNameContainsOrLastNameContainingAllIgnoreCase(String userName, String name, String lName);

    User findUserById(Long id);
}
