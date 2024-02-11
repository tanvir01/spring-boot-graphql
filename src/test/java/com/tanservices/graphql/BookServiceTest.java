package com.tanservices.graphql;

import com.tanservices.graphql.domain.Book;
import com.tanservices.graphql.domain.Genre;
import com.tanservices.graphql.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        bookService.init();
    }

    @Test
    public void testFindAll() {
        List<Book> books = bookService.findAll();
        assertNotNull(books);
        assertTrue(books.size() > 0);
    }

    @Test
    public void testFindById() {
        Optional<Book> book = bookService.findById(1);
        assertTrue(book.isPresent());
        assertEquals(1, book.get().id());
    }

    @Test
    public void testCreateBook() {
        Book newBook = bookService.createBook("Test Book", 10.99, Genre.SCIFI);
        assertNotNull(newBook);
        assertEquals("Test Book", newBook.title());
        assertEquals(10.99, newBook.price());
        assertEquals(Genre.SCIFI, newBook.genre());
    }

    @Test
    public void testUpdateBook() {
        Book updatedBook = bookService.updateBook(1, "Updated Book", 11.99, Genre.FANTASY);
        assertNotNull(updatedBook);
        assertEquals("Updated Book", updatedBook.title());
        assertEquals(11.99, updatedBook.price());
        assertEquals(Genre.FANTASY, updatedBook.genre());
    }

    @Test
    public void testDeleteBook() {
        Book deletedBook = bookService.deleteBook(1);
        assertNotNull(deletedBook);
        assertEquals(1, deletedBook.id());
    }
}
