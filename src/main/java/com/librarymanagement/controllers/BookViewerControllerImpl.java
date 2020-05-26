/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.librarymanagement.controllers;

import com.librarymanagement.LibraryManagement;
import com.librarymanagement.entities.Book;
import com.librarymanagement.models.BookViewerModel;
import com.librarymanagement.utils.Inject;
import com.librarymanagement.views.About;
import com.librarymanagement.views.BookViewer;
import java.util.List;

/**
 *
 * @author ngotrung
 */
public class BookViewerControllerImpl implements BookViewerController{

    private BookViewerModel bookViewerModel;
    private BookViewer bookViewer;
    private LibraryManagement libraryManagement;
    private About about;
    private Book book;
    
    @Override
    public void updateView() {
        bookViewer.updateBooks(bookViewerModel.getAllBooks());
    }

    @Inject(componentName = "bookViewerModel")
    public void setBookViewerModel(BookViewerModel bookViewerModel) {
        this.bookViewerModel = bookViewerModel;
    }

    @Inject(componentName = "bookViewer")
    public void setBookViewer(BookViewer bookViewer) {
        this.bookViewer = bookViewer;
    }

    @Inject(componentName = "libraryManagement")
    public void setLibraryManagement(LibraryManagement libraryManagement) {
        this.libraryManagement = libraryManagement;
    }

    @Inject(componentName = "about")
    public void setAbout(About about) {
        this.about = about;
    }
    

    @Override
    public void onAboutButtonClicked() {
        libraryManagement.setView(about);
    }

    @Override
    public void onDeleteButtonClicked() {
        
    }

    @Override
    public void onAddButtonClicked() {
        
    }

    @Override
    public void onBookTableMouseClicked() {
        List<Book> books = bookViewer.getBookTableModel().getBooks();
        int selectedRow = bookViewer.getBookViewTable().getSelectedRow();
        book = books.get(selectedRow);
        
    }
}
