package com.librarymanagement.controllers;

import com.librarymanagement.LibraryManagement;
import com.librarymanagement.entities.Book;
import com.librarymanagement.models.BookViewerModel;
import com.librarymanagement.utils.Inject;
import com.librarymanagement.views.About;
import com.librarymanagement.views.BookViewer;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;

public class BookViewerController {

    private BookViewerModel bookViewerModel;
    private BookViewer bookViewer;
    private LibraryManagement libraryManagement;
    private About about;
    private Book book;
    

    public void updateView() {
        bookViewer.updateBooks(bookViewerModel.getAllBooks());
        System.out.println(bookViewerModel.getAllBooks());
        bookViewer.getCategoryComboBox().setModel(new DefaultComboBoxModel());
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
        bookViewer.getCloseButton().addActionListener(this::onCloseButtonActionPerfromed);
        bookViewer.getBookViewTable().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onBookTableMouseClicked(e);
            }
            
        });
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
        Book newBook = new Book();
        newBook.setId(bookViewer.getBookIDTextFiled().getText());
        newBook.setTitle(bookViewer.getTitleTextField().getText());
        newBook.setAuthor(bookViewer.getAuthorTextField().getText());
        newBook.setCategory(bookViewer.getCategoryComboBox().getSelectedItem().toString());
        newBook.setKeyword(bookViewer.getKeywordTextField().getText());
        
        bookViewerModel.addNewBook(newBook);
        updateView();
    }

    private void onDeleteButtonActionPerformed(ActionEvent e) {
        
    }

    private void onUpdateButtonActionPerformed(ActionEvent ae) {
        book.setId(bookViewer.getBookIDTextFiled().getText());
        book.setTitle(bookViewer.getTitleTextField().getText());
        book.setAuthor(bookViewer.getAuthorTextField().getText());
        book.setCategory(bookViewer.getCategoryComboBox().getSelectedItem().toString());
        book.setKeyword(bookViewer.getKeywordTextField().getText());
        bookViewerModel.updateBook(book);
        updateView();
    }

    private void onCloseButtonActionPerfromed(ActionEvent e) {
        System.exit(0);
    }
    
    private void onBookTableMouseClicked(MouseEvent e) {
        displayBook(book = getSelectedBook());
    }
    
    private Book getSelectedBook() {
        int selectedRow = bookViewer.getBookViewTable().getSelectedRow();
        return bookViewer.getBookTableModel().getBooks().get(selectedRow);
    }
    
    private void displayBook(Book book) {
        bookViewer.getBookIDTextFiled().setText(book.getId());
        bookViewer.getTitleTextField().setText(book.getTitle());
        bookViewer.getAuthorTextField().setText(book.getAuthor());
        //bookViewer.getCategoryField().setText(book.getCategory);
        bookViewer.getKeywordTextField().setText(book.getKeyword());
        String[] catIDs = bookViewerModel.getCategoryIDs();
        bookViewer.getCategoryComboBox().setModel(new DefaultComboBoxModel(catIDs));
        bookViewer.getCategoryComboBox().setSelectedItem(book.getCategory());
        
        
    }
}
