package com.alexdarin.bookstore.dao;

import com.alexdarin.bookstore.entity.Author;
import com.alexdarin.bookstore.entity.AuthorCreateRequest;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import java.util.List;

@Setter
public class AuthorDao extends NamedParameterJdbcDaoSupport {

    private String getAllAuthorsQuery;
    private String getAuthorById;
    private String getAuthorsBooksQuery;
    private String getNextIdQuery;
    private String createAuthorQuery;
    private String updateAuthorById;
    private String deleteAuthorQuery;


    @Autowired
    public AuthorDao(final JdbcTemplate jdbcTemplate) {
        setJdbcTemplate(jdbcTemplate);
    }


    public List<Author> listAllAuthors() {
        return getNamedParameterJdbcTemplate().query(getAllAuthorsQuery, (resultSet, rowNum) -> {

            final MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            int authorId = resultSet.getInt("id");

            parameterSource.addValue("author_id", authorId);

            return Author.builder()
                    .id(resultSet.getInt("id"))
                    .firstName(resultSet.getString("first_name"))
                    .lastName(resultSet.getString("last_name"))
                    .amountOfBooks(
                            getNamedParameterJdbcTemplate()
                                    .queryForObject(getAuthorsBooksQuery, parameterSource, Integer.class))
                    .build();
        });
    }

    public Author createAuthor(final AuthorCreateRequest request) {
        final int authorId = getNamedParameterJdbcTemplate()
                .queryForObject(getNextIdQuery, new MapSqlParameterSource(), Integer.class);
        return executeCreateUpdateQuery(authorId, request, createAuthorQuery);
    }


    public Author editAuthor(final int id, final AuthorCreateRequest author) {
        return executeCreateUpdateQuery(id, author, updateAuthorById);
    }

    public Author deleteAuthor(final int id) {
        final MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue("author_id", id);

        final Author author = getNamedParameterJdbcTemplate()
                .queryForObject(getAuthorById, parameterSource, (resultSet, rowNum) -> Author.builder()
                        .id(resultSet.getInt("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .amountOfBooks(
                                getNamedParameterJdbcTemplate()
                                        .queryForObject(getAuthorsBooksQuery, parameterSource, Integer.class))
                        .build());

        getNamedParameterJdbcTemplate().update(deleteAuthorQuery, parameterSource);

        return author;
    }

    private Author executeCreateUpdateQuery(final int id, final AuthorCreateRequest author, final String query) {

        final MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue("author_id", id);
        parameterSource.addValue("first_name", author.getFirstName());
        parameterSource.addValue("last_name", author.getLastName());
        parameterSource.addValue("amount_of_books", author.getAmountOfBooks());

        getNamedParameterJdbcTemplate().update(query, parameterSource);

        return Author.builder().id(id).firstName(author.getFirstName()).lastName(author.getLastName())
                .amountOfBooks(author.getAmountOfBooks()).build();
    }


    public void setGetAllAuthorsQuery(final String getAllAuthorsQuery) {
        this.getAllAuthorsQuery = getAllAuthorsQuery;
    }

    public void setGetAuthorById(final String getAuthorById) {
        this.getAuthorById = getAuthorById;
    }

    public void setGetAuthorsBooksQuery(final String getAuthorsBooksQuery) {
        this.getAuthorsBooksQuery = getAuthorsBooksQuery;
    }

    public void setGetNextIdQuery(final String getNextIdQuery) {
        this.getNextIdQuery = getNextIdQuery;
    }

    public void setCreateAuthorQuery(final String createAuthorQuery) {
        this.createAuthorQuery = createAuthorQuery;
    }

    public void setUpdateAuthorById(String updateAuthorById) {
        this.updateAuthorById = updateAuthorById;
    }

    public void setDeleteAuthorQuery(String deleteAuthorQuery) {
        this.deleteAuthorQuery = deleteAuthorQuery;
    }
}
