package com.tanservices.graphql.service;

import com.tanservices.graphql.domain.Genre;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import com.tanservices.graphql.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();

    AtomicInteger id = new AtomicInteger(0);

    public List<Book> findAll() {
        return books;
    }

    public Optional<Book> findById(Integer id) {
        return books.stream().filter(book -> book.id().equals(id)).findFirst();
    }

    public Book createBook(String title, Double price, Genre genre) {
        Book book = new Book(id.incrementAndGet(), title, price, genre);
        books.add(book);
        return book;
    }

    public Book updateBook(Integer id, String title, Double price, Genre genre) {
        Book updatedBook = new Book(id, title, price, genre);
        Optional<Book> bookOptional = findById(id);
        if (!bookOptional.isPresent()) {
            throw new IllegalArgumentException("Book not found");
        }

        Book book = bookOptional.get();
        int index = books.indexOf(book);
        books.set(index, updatedBook);

        return updatedBook;
    }

    public Book deleteBook(Integer id) {
        Optional<Book> bookOptional = findById(id);
        if (!bookOptional.isPresent()) {
            throw new IllegalArgumentException("Book not found");
        }

        Book book = bookOptional.get();
        books.remove(book);

        return book;
    }

    @PostConstruct
    public void init(){
        books.add(new Book(id.incrementAndGet(), "The Hitchhiker's Guide to the Galaxy", 5.99, Genre.HORROR));
        books.add(new Book(id.incrementAndGet(), "The Lord of the Rings", 9.99, Genre.FANTASY));
        books.add(new Book(id.incrementAndGet(), "Brave New World", 7.99, Genre.SCIFI));
        books.add(new Book(id.incrementAndGet(), "1984", 6.99, Genre.SCIFI));
        books.add(new Book(id.incrementAndGet(), "The Great Gatsby", 8.99, Genre.THRILLER));
        books.add(new Book(id.incrementAndGet(), "To Kill a Mockingbird", 6.99, Genre.MYSTERY));
        books.add(new Book(id.incrementAndGet(), "The Catcher in the Rye", 7.99, Genre.ROMANCE));
    }
}
