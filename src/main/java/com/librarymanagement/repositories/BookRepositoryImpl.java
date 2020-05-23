package com.librarymanagement.repositories;

import com.librarymanagement.entities.Book;
import com.librarymanagement.utils.InjectBy;
import com.librarymanagement.utils.InjectDependency;
import lombok.Setter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository{

    @InjectDependency(injectBy = InjectBy.NAME, dependencyName = "connection")
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private Connection connection;


    @Override
    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM Books";
        ArrayList<Book> books = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                Book book = new Book();

                book.setId(rs.getString("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setCategory(rs.getString("Category"));
                book.setKeyword(rs.getString("Keyword"));

                books.add(book);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return books;
    }

    @Override
    public void addNewBook(Book book) {
        String sql = "INSERT INTO Books VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getCategory());
            statement.setString(5, book.getKeyword());

            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean updateBook(Book book) {
        String sql = "UPDATE Books SET BookID=?, Title=?, Author=?, Category=? Keyword=?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getCategory());
            statement.setString(5, book.getKeyword());

            return statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public void removeBook(String bookID) {

    }

    @Override
    public Book getBook(String bookID) {
        String sql = "SELECT * FROM Books WHERE BookID=?";
        ArrayList<Book> books = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bookID);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
            {
                Book book = new Book();

                book.setId(rs.getString("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setCategory(rs.getString("Category"));
                book.setKeyword(rs.getString("Keyword"));

                return book;
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> searchByTitle(String title) {
        String sql = "SELECT * FROM Books WHERE Title=?";
        ArrayList<Book> books = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                Book book = new Book();

                book.setId(rs.getString("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setCategory(rs.getString("Category"));
                book.setKeyword(rs.getString("Keyword"));

                books.add(book);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> searchByCategory(String category) {
        String sql = "SELECT * FROM Books WHERE Category=?";
        ArrayList<Book> books = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                Book book = new Book();

                book.setId(rs.getString("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setCategory(rs.getString("Category"));
                book.setKeyword(rs.getString("Keyword"));

                books.add(book);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> searchByKeyword(String keyword) {
        String sql = "SELECT * FROM Books WHERE Keyword=?";
        ArrayList<Book> books = new ArrayList<>();
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, keyword);
            ResultSet rs = statement.executeQuery();
            while(rs.next())
            {
                Book book = new Book();

                book.setId(rs.getString("BookID"));
                book.setTitle(rs.getString("Title"));
                book.setAuthor(rs.getString("Author"));
                book.setCategory(rs.getString("Category"));
                book.setKeyword(rs.getString("Keyword"));

                books.add(book);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return books;
    }
}
