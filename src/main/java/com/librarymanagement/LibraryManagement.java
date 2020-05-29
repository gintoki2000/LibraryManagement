package com.librarymanagement;

import com.librarymanagement.controllers.BookViewerController;
import com.librarymanagement.repositories.ComponentCreationException;
import com.librarymanagement.utils.ComponenContainer;
import com.librarymanagement.views.BookViewer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class LibraryManagement extends JFrame {

    public LibraryManagement() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void setView(JPanel view) {
        setContentPane(view);
        pack();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        try {
            ComponenContainer container = new ComponenContainer(Context.class);
            LibraryManagement libraryManagement = (LibraryManagement) container.getComponent("libraryManagement");
            BookViewerController bookViewerController = (BookViewerController) container.getComponent("bookViewerController");
            BookViewer bookViewer = (BookViewer) container.getComponent("bookViewer");

            libraryManagement.setView(bookViewer);

            bookViewerController.updateView();
        } catch (ComponentCreationException ex) {
            Logger.getLogger(LibraryManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
