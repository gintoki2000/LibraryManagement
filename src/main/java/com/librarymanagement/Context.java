package com.librarymanagement;

import com.librarymanagement.controllers.BookViewerController;
import com.librarymanagement.models.BookViewerModel;
import com.librarymanagement.models.BookViewerModelImpl;
import com.librarymanagement.repositories.BookRepository;
import com.librarymanagement.repositories.BookRepositoryImpl;
import com.librarymanagement.repositories.CategoryRepository;
import com.librarymanagement.repositories.CategoryRepositoryImpl;
import com.librarymanagement.utils.Component;
import com.librarymanagement.views.About;
import com.librarymanagement.views.BookViewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Context {

    @Component
    public Connection connection() throws SQLException {
        Properties properties = new Properties();
        properties.put("user", Config.USER_NAME);
        properties.put("password", Config.PASSWORD);
        properties.put("databaseName", Config.DATABASE_NAME);

        return DriverManager.getConnection(Config.DB_URL, properties);

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
    public BookViewer bookViewer() {
        return new BookViewer();
    }

    @Component
    public BookViewerModel bookViewerModel() {
        return new BookViewerModelImpl();
    }

    @Component
    public BookViewerController bookViewerController() {
        return new BookViewerController();
    }

    @Component
    public About about() {
        return new About();
    }
}
