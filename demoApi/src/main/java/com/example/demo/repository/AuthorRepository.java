package com.example.demo.repository;

import com.example.demo.model.Author;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;

@EntityScan
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
