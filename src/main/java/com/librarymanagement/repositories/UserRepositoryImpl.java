package com.librarymanagement.repositories;

import com.librarymanagement.ConnectionHelper;
import com.librarymanagement.entities.User;
import com.librarymanagement.repositories.UserRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ngotrung
 */
public class UserRepositoryImpl implements UserRepository {

    private ConnectionHelper connectionHelper;

    @Override
    public void addUser(User user) throws SQLException {
        String sql = "INSTERT INTO Users VALUES(?, ?, ?, ?, ?, ?)";
        try ( Connection con = connectionHelper.getConnection();  PreparedStatement pstatement = con.prepareStatement(sql)) {
            pstatement.setString(1, user.getUserID());
            pstatement.setString(2, user.getFullName());
            pstatement.setString(3, user.getEmail());
            pstatement.setString(4, user.getPassword());
            pstatement.setString(5, user.getStatus());
            pstatement.setInt(6, user.getUserRight());
            pstatement.execute();
        }
    }

    @Override
    public Optional<User> getUser(String UID) throws SQLException {
        String sql = "SELECT FROM Users WHERE UserID=\'" + UID + "\'";
        try ( Connection con = connectionHelper.getConnection();  Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()) {
                User user = new User();
                user.setUserID(rs.getString("UserID"));
                user.setFullName(rs.getString("FullName"));
                user.setEmail(rs.getString("Email"));
                user.setPassword(rs.getString("Password"));
                user.setStatus(rs.getString("Status"));
                user.setUserRight(rs.getInt("UserRight"));
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    public UserRepositoryImpl(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
    }

    
    
    

}
