package com.tanservices.graphql.controller;

import com.tanservices.graphql.domain.Book;
import com.tanservices.graphql.domain.Genre;
import com.tanservices.graphql.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @QueryMapping
    public Optional<Book> findById(@Argument Integer id) {
        return bookService.findById(id);
    }

    @MutationMapping
    public Book createBook(@Argument String title, @Argument Double price, @Argument Genre genre) {
        return bookService.createBook(title, price, genre);
    }

    @MutationMapping
    public Book updateBook(@Argument Integer id, @Argument String title, @Argument Double price, @Argument Genre genre) {
        return bookService.updateBook(id, title, price, genre);
    }

    @MutationMapping
    public Book deleteBook(@Argument Integer id) {
        return bookService.deleteBook(id);
    }
}
