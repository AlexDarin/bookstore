package com.alexdarin.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateRequest {
    private String title;
    private String isbn;
    private List<Author> authors;
}
