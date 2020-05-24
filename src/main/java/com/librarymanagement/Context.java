package com.librarymanagement;

import com.librarymanagement.repositories.BookRepository;
import com.librarymanagement.repositories.BookRepositoryImpl;
import com.librarymanagement.repositories.CategoryRepository;
import com.librarymanagement.repositories.CategoryRepositoryImpl;
import com.librarymanagement.utils.Component;
import com.librarymanagement.views.TestView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Context {
    @Component
    public Connection connection() {
        Properties properties = new Properties();
        properties.put("user", Config.USER_NAME);
        properties.put("password", Config.PASSWORD);
        properties.put("databaseName", Config.DATABASE_NAME);
        try {
            return DriverManager.getConnection(Config.DB_URL, properties);
        } catch (SQLException sqlException) {
            System.out.println("Failed to connect to database server!!!");
            sqlException.printStackTrace();
        }
        return null;
    }

    @Component
    public BookRepository bookRepository() {
        return new BookRepositoryImpl();
    }

    @Component
    public CategoryRepository categoryRepository() {
        return new CategoryRepositoryImpl();
    }

    @Component
    public LibraryManagement libraryManagement() {
        return new LibraryManagement();
    }

    @Component
    public JPanel mainView() {
        return new TestView();
    }
}
