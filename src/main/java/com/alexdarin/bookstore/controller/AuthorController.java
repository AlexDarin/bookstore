package com.alexdarin.bookstore.controller;

import com.alexdarin.bookstore.entity.Author;
import com.alexdarin.bookstore.entity.AuthorCreateRequest;
import com.alexdarin.bookstore.service.AuthorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(final AuthorService authorService) {
        this.authorService = authorService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Lists all authors.",
            notes = "Returns list of book authors.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Author> listAuthors() {
        return authorService.listAuthors();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(
            value = "Registers a new author.",
            notes = "Returns registered author description.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Author createAuthor(@RequestBody final AuthorCreateRequest request) {
        return authorService.createAuthor(request);
    }

    @PostMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(
            value = "Edits existing author.",
            notes = "Returns the descriptor for the author that was edited.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Author editAuthor(@PathVariable("id") int id, @RequestBody AuthorCreateRequest author) {
        return authorService.editAuthor(id, author);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(
            value = "Unregisters a specific author.",
            notes = "Returns the description for the author that was deleted.",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Author deleteAuthor(@PathVariable("id") final int id) {
        return authorService.deleteAuthor(id);
    }
}
