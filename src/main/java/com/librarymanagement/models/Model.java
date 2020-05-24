package com.librarymanagement.models;

import com.librarymanagement.entities.Book;

import java.util.List;

public interface Model {
    List<Book> getAllBooks();
}
