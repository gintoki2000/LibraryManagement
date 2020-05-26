package com.librarymanagement;

import com.librarymanagement.controllers.BookViewerController;
import com.librarymanagement.repositories.BookRepository;
import com.librarymanagement.utils.ComponenContainer;
import com.librarymanagement.utils.Inject;
import com.librarymanagement.views.BookViewer;

import javax.swing.*;


public class LibraryManagement extends JFrame {
    public LibraryManagement() {
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException e) {
                    e.printStackTrace();
            } catch (InstantiationException e) {
                    e.printStackTrace();
            } catch (IllegalAccessException e) {
                    e.printStackTrace();
            } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
            }
    }
    
    public void setView(JPanel view) {
        setContentPane(view);
        pack();
    }

    public static void main(String[] args) {
            ComponenContainer container = new ComponenContainer(Context.class);
            LibraryManagement libraryManagement = (LibraryManagement) container.getComponent("libraryManagement");
            BookViewerController bookViewerController = (BookViewerController) container.getComponent("bookViewerController");
            BookViewer bookViewer = (BookViewer) container.getComponent("bookViewer");
            
            libraryManagement.setView(bookViewer);
            
            bookViewerController.updateView();
                    
    }
}
