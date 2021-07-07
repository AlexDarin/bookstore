package com.alexdarin.bookstore.service;

import com.alexdarin.bookstore.dao.AuthorDao;
import com.alexdarin.bookstore.dao.BookDao;
import com.alexdarin.bookstore.entity.Author;
import com.alexdarin.bookstore.entity.AuthorCreateRequest;
import com.alexdarin.bookstore.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorDao authorDao;

    @Autowired
    public AuthorService(final AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    public List<Author> listAuthors() {
        return authorDao.listAllAuthors();
    }

    public Author createAuthor(final AuthorCreateRequest request) {
        return authorDao.createAuthor(request);
    }

    public Author editAuthor(final int id, final AuthorCreateRequest author) {
        return authorDao.editAuthor(id, author);
    }

    public Author deleteAuthor(final int id) {
        return authorDao.deleteAuthor(id);
    }
}
