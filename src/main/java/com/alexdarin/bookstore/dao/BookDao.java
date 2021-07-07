package com.alexdarin.bookstore.dao;

import com.alexdarin.bookstore.entity.Author;
import com.alexdarin.bookstore.entity.Book;
import com.alexdarin.bookstore.entity.BookCreateRequest;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import java.util.List;

@Setter
public class BookDao extends NamedParameterJdbcDaoSupport {

    private String getAllBooksQuery;
    private String getBookAuthorsQuery;
    private String getNextIdQuery;
    private String createBookQuery;
    private String deleteBookQuery;
    private String getBookById;
    private String updateBookById;
    private String updateAuthorsBooks;
    private String deleteAuthorsBooksLine;

    @Autowired
    public BookDao(final JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
    }

    public List<Book> listAllBooks() {

        return getNamedParameterJdbcTemplate().query(getAllBooksQuery, (resultSet, rowNum) -> {
            final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            int bookId = resultSet.getInt("id");
            parameterSource.addValue("book_id", bookId);
            return Book.builder()
                    .id(resultSet.getInt("id"))
                    .title(resultSet.getString("title"))
                    .isbn(resultSet.getString("isbn"))
                    .authors(getNamedParameterJdbcTemplate().query(getBookAuthorsQuery, parameterSource, (rs, rn) -> Author.builder()
                            .id(rs.getInt("id"))
                            .firstName(rs.getString("first_name"))
                            .lastName(rs.getString("last_name"))
                            .amountOfBooks(rs.getInt("amount_of_books"))
                            .build()))
                    .build();
        });
    }

    public Book createBook(final BookCreateRequest request) {
        final int bookId = getNamedParameterJdbcTemplate()
                .queryForObject(getNextIdQuery, new MapSqlParameterSource(), Integer.class);
        return executeCreateUpdateQuery(bookId, request, createBookQuery);
    }

    public Book editBook(final int id, final BookCreateRequest book) {
        return executeCreateUpdateQuery(id, book, updateBookById);
    }

    public Book deleteBook(final int id) {
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("book_id", id);

        final Book book = getNamedParameterJdbcTemplate()
                .queryForObject(getBookById, parameterSource, (resultSet, rowNum) -> Book.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .isbn(resultSet.getString("isbn"))
                        .authors(getNamedParameterJdbcTemplate().query(getBookAuthorsQuery, parameterSource, (rs, rn) -> Author.builder()
                                .id(rs.getInt("id"))
                                .firstName(rs.getString("first_name"))
                                .lastName(rs.getString("last_name"))
                                .amountOfBooks(rs.getInt("amount_of_books"))
                                .build()))
                        .build());

        getNamedParameterJdbcTemplate().update(deleteBookQuery, parameterSource);

        return book;
    }

    private Book executeCreateUpdateQuery(int id, BookCreateRequest book, String query) {
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        parameterSource.addValue("title", book.getTitle());
        parameterSource.addValue("isbn", book.getIsbn());
        parameterSource.addValue("authors", book.getAuthors());

        getNamedParameterJdbcTemplate().update(query, parameterSource);

        return Book.builder().id(id).title(book.getTitle()).isbn(book.getIsbn()).authors(book.getAuthors()).build();
    }

    public void setGetAllBooksQuery(final String getAllBooksQuery) {
        this.getAllBooksQuery = getAllBooksQuery;
    }

    public void setGetBookAuthorsQuery(final String getBookAuthorsQuery) {
        this.getBookAuthorsQuery = getBookAuthorsQuery;
    }

    public void setGetNextIdQuery(final String getNextIdQuery) {
        this.getNextIdQuery = getNextIdQuery;
    }

    public void setCreateBookQuery(final String createBookQuery) {
        this.createBookQuery = createBookQuery;
    }

    public void setDeleteBookQuery(final String deleteBookQuery) {
        this.deleteBookQuery = deleteBookQuery;
    }

    public void setGetBookById(final String getBookById) {
        this.getBookById = getBookById;
    }

    public void setUpdateBookById(final String updateBookById) {
        this.updateBookById = updateBookById;
    }

    public void setUpdateAuthorsBooks(String updateAuthorsBooks) {
        this.updateAuthorsBooks = updateAuthorsBooks;
    }

    public void setDeleteAuthorsBooksLine(String deleteAuthorsBooksLine) {
        this.deleteAuthorsBooksLine = deleteAuthorsBooksLine;
    }
}
