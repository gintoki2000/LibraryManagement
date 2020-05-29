package com.librarymanagement.controllers;

import com.librarymanagement.LibraryManagement;
import com.librarymanagement.entities.Book;
import com.librarymanagement.models.BookViewerModel;
import com.librarymanagement.utils.Inject;
import com.librarymanagement.views.About;
import com.librarymanagement.views.BookViewer;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import sun.tools.jconsole.JConsole;

public class BookViewerController {

    private BookViewerModel bookViewerModel;
    private BookViewer bookViewer;
    private LibraryManagement libraryManagement;
    private About about;
    private Book book;

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
        
        bookViewer.getAboutButton().addActionListener(this::onAboutButtonActionPerformed);
        bookViewer.getAddButton().addActionListener(this::onAddButtonActionPerformed);
        bookViewer.getDeleteButton().addActionListener(this::onDeleteButtonActionPerformed);
        bookViewer.getUpdateButton().addActionListener(this::onUpdateButtonActionPerformed);
        
    }

    @Inject(componentName = "libraryManagement")
    public void setLibraryManagement(LibraryManagement libraryManagement) {
        this.libraryManagement = libraryManagement;
    }

    @Inject(componentName = "about")
    public void setAbout(About about) {
        this.about = about;
    }

    private void onAboutButtonActionPerformed(ActionEvent e) {
        EventQueue.invokeLater(() -> {
            JFrame aboutWindow = new JFrame();
            aboutWindow.setContentPane(about);
            aboutWindow.setVisible(true);
            aboutWindow.pack();
        });
    }

    private void onAddButtonActionPerformed(ActionEvent e) {
        Book book = new Book();
        book.setId(bookViewer.getBookIDTextFiled().getText());
        book.setTitle(bookViewer.getTitleTextField().getText());
    }

    private void onDeleteButtonActionPerformed(ActionEvent e) {
        
    }

    private void onUpdateButtonActionPerformed(ActionEvent ae) {
        
    }

}
