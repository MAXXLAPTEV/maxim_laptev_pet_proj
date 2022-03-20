package com.example.Diplom.repo;

import com.example.Diplom.ent.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookAuthorRepo extends JpaRepository<BookAuthor, Long> {
    Optional<BookAuthor> findByAuthorNameEqualsAndAuthorSurnameEquals(String authorName, String authorSurname);




}
