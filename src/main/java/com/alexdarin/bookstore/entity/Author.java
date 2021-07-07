package com.alexdarin.bookstore.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    private int id;
    private String firstName;
    private String lastName;
    private int amountOfBooks;
}
