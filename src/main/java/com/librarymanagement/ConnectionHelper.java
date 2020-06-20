package com.librarymanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHelper {
    private String user;
    private String password;

    public Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        properties.put("user", user);
        properties.put("password", password);
        properties.put("databaseName", Config.DATABASE_NAME);
        return DriverManager.getConnection(Config.DB_URL, properties);
    }

    public ConnectionHelper(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public ConnectionHelper() {
        this("", "");
    }
    
    

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
