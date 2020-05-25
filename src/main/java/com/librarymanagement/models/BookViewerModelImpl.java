/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.librarymanagement.models;

import com.librarymanagement.entities.Book;
import com.librarymanagement.repositories.BookRepository;
import com.librarymanagement.utils.Inject;
import java.util.List;

/**
 *
 * @author ngotrung
 */
public class BookViewerModelImpl implements BookViewerModel{
    
    private BookRepository bookRepository;
    
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }

    @Inject
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    
    
}
