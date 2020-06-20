package com.librarymanagement.repositories;

import com.librarymanagement.entities.Book;

import java.sql.SQLException;
import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks() throws SQLException;
    boolean addNewBook(Book book) throws SQLException;
    boolean updateBook(Book book) throws SQLException;
    boolean removeBook(String bookID) throws SQLException;
    Book getBook(String bookID) throws SQLException;
    
    List<Book> search(String value, String by) throws SQLException;
    
    List<Book> search(String value, String by, String groupBy) throws SQLException;
    
    String[] COLUMN_NAMES = {"BookID", "Title", "Author", "Category", "Keyword"};
    
}
