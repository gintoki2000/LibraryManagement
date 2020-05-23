package com.librarymanagement.repositories;

import com.librarymanagement.entities.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getAllBooks();
    void addNewBook(Book book);
    boolean updateBook(Book book);
    void removeBook(String bookID);
    Book getBook(String bookID);
    List<Book> searchByTitle(String title);
    List<Book> searchByCategory(String category);
    List<Book> searchByKeyword(String keyword);
}
