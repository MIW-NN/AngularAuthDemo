package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequiredArgsConstructor
@PreAuthorize("")
public class AuthorController {
    private final AuthorRepository authorRepository;

    @GetMapping("/authors")
    protected List<Author> showAuthorOverview() {
        return authorRepository.findAll();
    }

    @PostMapping("/authors/new")
    protected List<Author> saveOrUpdateAuthor(@ModelAttribute("newAuthor") Author author, BindingResult result) {
        if (!result.hasErrors()) {
            authorRepository.save(author);
        }

        return showAuthorOverview();
    }
}
