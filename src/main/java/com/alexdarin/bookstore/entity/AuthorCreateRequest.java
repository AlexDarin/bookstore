package com.alexdarin.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorCreateRequest {
    private String firstName;
    private String lastName;
    private int amountOfBooks;
}
