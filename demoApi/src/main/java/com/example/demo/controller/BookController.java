package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequiredArgsConstructor
public class BookController {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @GetMapping("/books")
    private List<Book> showBookOverview() {
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    private Book showEditBookForm(@PathVariable("id") long id) {
        return bookRepository.findById(id).orElseGet(() -> null);
    }


    @DeleteMapping("/book/{id}")
    private void deleteBook(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @PostMapping("/book")
    private void saveOrUpdateBook(@RequestBody Book bookToBeSaved) {
        bookRepository.save(bookToBeSaved);
    }
//
//    @GetMapping("/book/detail/{title}")
//    private String showBookDetails(@PathVariable("title") String title, Model model) {
//        Optional<Book> optionalBook = bookRepository.findBookByTitle(title);
//
//        if (optionalBook.isEmpty()) {
//            return "redirect:/book/overview";
//        }
//
//        model.addAttribute("bookToBeShown", optionalBook.get());
//
//        return "bookDetail";
//    }
}
