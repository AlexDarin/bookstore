package com.alexdarin.bookstore.service;

import com.alexdarin.bookstore.dao.BookDao;
import com.alexdarin.bookstore.entity.Book;
import com.alexdarin.bookstore.entity.BookCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<Book> listBooks() {
        return bookDao.listAllBooks();
    }

    public Book createBook(final BookCreateRequest request) {
        return bookDao.createBook(request);
    }

    public Book editBook(final int id, final BookCreateRequest book) {
        return bookDao.editBook(id, book);
    }

    public Book deleteBook(final int id) {
        return bookDao.deleteBook(id);
    }


}
