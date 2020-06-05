package com.librarymanagement.models;

import com.librarymanagement.entities.Book;

import java.util.List;

public interface BookViewerModel {
    List<Book> getAllBooks();
    String[] getCategoryIDs();
    void addNewBook(Book book);
    boolean deleteBook(Book book);
    boolean updateBook(Book book);
    
}
