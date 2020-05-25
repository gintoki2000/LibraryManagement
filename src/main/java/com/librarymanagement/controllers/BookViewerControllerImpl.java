/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.librarymanagement.controllers;

import com.librarymanagement.models.BookViewerModel;
import com.librarymanagement.utils.Inject;
import com.librarymanagement.views.BookViewer;

/**
 *
 * @author ngotrung
 */
public class BookViewerControllerImpl implements BookViewerController{

    BookViewerModel bookViewerModel;
    BookViewer bookViewer;
    
    @Override
    public void updateView() {
        bookViewer.updateBooks(bookViewerModel.getAllBooks());
    }

    @Inject
    public void setBookViewerModel(BookViewerModel bookViewerModel) {
        this.bookViewerModel = bookViewerModel;
    }

    @Inject
    public void setBookViewer(BookViewer bookViewer) {
        this.bookViewer = bookViewer;
    }
    
    
}
