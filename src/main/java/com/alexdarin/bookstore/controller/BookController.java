package com.alexdarin.bookstore.controller;

import com.alexdarin.bookstore.entity.Book;
import com.alexdarin.bookstore.entity.BookCreateRequest;
import com.alexdarin.bookstore.service.BookService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Lists all books.",
            notes = "Returns list of books in the store.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Book> listBooks() {
        return bookService.listBooks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(
            value = "Registers book in the store.",
            notes = "Returns added book descriptor.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Book createBook(@RequestBody final BookCreateRequest request) {
        return bookService.createBook(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Unregisters a specific book.",
            notes = "Returns the descriptor for the book that was deleted.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Book deleteBook(@PathVariable("id") final int id) {
        return bookService.deleteBook(id);
    }

    @PostMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(
            value = "Edits existing book.",
            notes = "Returns the descriptor for the book that was edited.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Book editBook(@PathVariable("id") int id, @RequestBody final BookCreateRequest book) {
        return bookService.editBook(id, book);
    }



}
