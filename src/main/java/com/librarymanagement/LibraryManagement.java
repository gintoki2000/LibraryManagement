package com.librarymanagement;


import com.librarymanagement.utils.Inject;
import javax.swing.*;

public class LibraryManagement extends JFrame {

    public LibraryManagement() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Inject(componentName = "bookViewer")
    public void setView(JPanel view) {
        setContentPane(view);
        pack();
    }
}
