package com.example.demo.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import com.example.demo.model.Book;
import com.example.demo.model.Copy;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CopyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/copy")
@RequiredArgsConstructor
public class CopyController {
    private final BookRepository bookRepository;
    private final CopyRepository copyRepository;

    @GetMapping("/new/{bookId}")
    private String createNewCopy(@PathVariable("bookId") Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isPresent()) {
            Copy copy = new Copy();
            copy.setBook(bookOptional.get());
            copyRepository.save(copy);
        }

        return "redirect:/book/overview";
    }

    @GetMapping("/borrow/{copyId}")
    private String makeCopyUnavailable(@PathVariable("copyId") Long copyId) {
        return setAvailabilityForCopyAndRedirectToBook(copyId, false);
    }

    @GetMapping("/return/{copyId}")
    private String makeCopyAvailable(@PathVariable("copyId") Long copyId) {
        return setAvailabilityForCopyAndRedirectToBook(copyId, true);
    }

    private String setAvailabilityForCopyAndRedirectToBook(Long copyId, boolean available) {
        Optional<Copy> optionalCopy = copyRepository.findById(copyId);

        if (optionalCopy.isEmpty()) {
            return "redirect:/book/overview";
        }

        Copy copy = optionalCopy.get();
        copy.setAvailable(available);
        copyRepository.save(copy);

        return String.format("redirect:/book/detail/%s", copy.getBook().getTitle());
    }

}
