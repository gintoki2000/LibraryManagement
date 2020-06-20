package com.librarymanagement.repositories;

import com.librarymanagement.ConnectionHelper;
import com.librarymanagement.entities.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    
    ConnectionHelper connectionHelper;
    
    public BookRepositoryImpl(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
    }
    
    @Override
    public List<Book> getAllBooks() throws SQLException {
        
        String sql = "SELECT * FROM Books";
        ArrayList<Book> books = new ArrayList<>();
        try ( Connection connection = connectionHelper.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                books.add(fromResultSet(rs));
            }
        }
        return books;
    }
    
    @Override
    public boolean addNewBook(Book book) throws SQLException {
        String sql = "INSERT INTO Books VALUES(?, ?, ?, ?, ?)";
        try ( Connection connection = connectionHelper.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getId());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getCategory());
            statement.setString(5, book.getKeyword());
            
            return statement.execute();
        }
    }
    
    @Override
    public boolean updateBook(Book book) throws SQLException {
        String sql = "UPDATE Books SET Title=?, Author=?, Category=?, Keyword=? WHERE BookID=?";
        try ( Connection connection = connectionHelper.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(5, book.getId());
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getCategory());
            statement.setString(4, book.getKeyword());
            
            return statement.execute();
        }
    }
    
    @Override
    public boolean removeBook(String bookID) throws SQLException {
        String sql = "DELETE FROM Books WHERE BookId=\'" + bookID + "\'";
        try ( Connection con = connectionHelper.getConnection();  Statement statement = con.createStatement()) {
            statement.execute(sql);
        }
        return false;
    }
    
    @Override
    public Book getBook(String bookID) throws SQLException {
        String sql = "SELECT * FROM Books WHERE BookID=?";
        try ( Connection connection = connectionHelper.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bookID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return fromResultSet(rs);
            }            
        }
        return null;
    }
    
    public List<Book> searchByTitle(String title) throws SQLException {
        String sql = "SELECT * FROM Books WHERE Title=?";
        ArrayList<Book> books = new ArrayList<>();
        try ( Connection connection = connectionHelper.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                books.add(fromResultSet(rs));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return books;
    }
    
    public List<Book> searchByCategory(String category) throws SQLException {
        String sql = "SELECT * FROM Books WHERE Category=?";
        ArrayList<Book> books = new ArrayList<>();
        try ( Connection connection = connectionHelper.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                books.add(fromResultSet(rs));
            }
        }
        return books;
    }
    
    public List<Book> searchByKeyword(String keyword) throws SQLException {
        String sql = "SELECT * FROM Books WHERE Keyword=?";
        ArrayList<Book> books = new ArrayList<>();
        try ( Connection connection = connectionHelper.getConnection();  PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, keyword);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                books.add(fromResultSet(rs));
            }
        }
        return books;
    }
    
    private Book fromResultSet(ResultSet rs) throws SQLException {
        Book book = new Book();
        
        book.setId(rs.getString("BookID"));
        book.setTitle(rs.getString("Title"));
        book.setAuthor(rs.getString("Author"));
        book.setCategory(rs.getString("Category"));
        book.setKeyword(rs.getString("Keyword"));
        
        return book;
    }
    
    @Override
    public List<Book> search(String value, String by) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Books WHERE "+ by+"=\'"+value +"\'" ;
        System.out.println(value + ", "+by);
        try (Connection con = connectionHelper.getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                books.add(fromResultSet(rs));
            }
        }
        return books;
    }

    @Override
    public List<Book> search(String value, String by, String groupBy) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Books WHERE "+ by+"=\'"+value +"\' ORDER BY " + groupBy;
        System.out.println(sql);
        try (Connection con = connectionHelper.getConnection(); PreparedStatement statement = con.prepareStatement(sql)) {
            
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                books.add(fromResultSet(rs));
            }
        }
        return books;
    }
    
    
}
