/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.librarymanagement.repositories;

import com.librarymanagement.entities.User;
import java.sql.SQLException;
import java.util.Optional;

/**
 *
 * @author ngotrung
 */
public interface UserRepository {
    void addUser(User user) throws SQLException;
    Optional<User> getUser(String UID) throws SQLException;
}
