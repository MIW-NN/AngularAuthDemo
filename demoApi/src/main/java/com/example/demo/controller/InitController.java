package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.model.Copy;
import com.example.demo.model.LibraryUser;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CopyRepository;
import com.example.demo.repository.LibraryUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequiredArgsConstructor
public class InitController {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CopyRepository copyRepository;
    private final LibraryUserRepository libraryUserRepository;
    private final PasswordEncoder passwordEncoder;
    @GetMapping("/initialize")
    private String initializeDB() {
        if (!libraryUserRepository.findAll().isEmpty()) {
            return "{\"text\":\"already initialized\"}";
        }

        LibraryUser adminUser = new LibraryUser();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("admin"));
        // TODO Enforce the user to select a better password
        System.err.println("Admin user created, please make sure to change the password");
        libraryUserRepository.save(adminUser);

        Author patrick = new Author("Patrick", "Rothfuss");
        Author paul = new Author("Paul", "van", "Loon");

        authorRepository.save(patrick);
        authorRepository.save(paul);

        Book wind = new Book("The Name of the Wind");
        wind.addAuthor(patrick);
        bookRepository.save(wind);

        ArrayList<Copy> copies = new ArrayList<>();

        copies.add(new Copy(wind));
        copies.add(new Copy(wind));
        copies.add(new Copy(wind));

        Book bus = new Book("De Griezelbus");
        bus.addAuthor(paul);
        bookRepository.save(bus);

        copies.add(new Copy(bus));
        copies.add(new Copy(bus));

        copyRepository.saveAll(copies);

        return "{\"text\":\"initialization done!\"}";
    }
}
