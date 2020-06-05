/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.librarymanagement.models;

import com.librarymanagement.entities.Book;
import com.librarymanagement.entities.Category;
import com.librarymanagement.repositories.BookRepository;
import com.librarymanagement.repositories.CategoryRepository;
import com.librarymanagement.utils.Inject;
import java.util.List;

/**
 *
 * @author ngotrung
 */
public class BookViewerModelImpl implements BookViewerModel {

    private BookRepository bookRepository;

    private CategoryRepository categoryRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.getAllBooks();
    }
    
    

    @Inject(componentName = "bookRepository")
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Inject(componentName = "categoryRepository")
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    

    @Override
    public String[] getCategoryIDs() {
        List<Category> categories = categoryRepository.getAllCategories();
        String[] ids = new String[categories.size()];
        for (int i = 0; i < ids.length; ++i) {
            ids[i] = categories.get(i).getID();
        }
        return ids;
    }

    @Override
    public boolean deleteBook(Book book) {
        return bookRepository.removeBook(book.getId());
    }

    @Override
    public boolean updateBook(Book book) {
        return bookRepository.updateBook(book);
    }

    @Override
    public void addNewBook(Book book) {
        bookRepository.addNewBook(book);
    }

    
}
