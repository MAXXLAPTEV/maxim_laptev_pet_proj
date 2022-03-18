package com.example.Diplom.repo;

import com.example.Diplom.ent.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookAuthorRepo extends JpaRepository<BookAuthor, Long> {
}
