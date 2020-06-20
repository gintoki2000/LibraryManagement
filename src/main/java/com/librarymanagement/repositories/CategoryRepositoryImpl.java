package com.librarymanagement.repositories;

import com.librarymanagement.ConnectionHelper;
import com.librarymanagement.entities.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    private ConnectionHelper connectionHelper;

    @Override
    public List<Category> getAllCategories() throws SQLException{
        List<Category> categories = new ArrayList<>();
        try (Connection connection = connectionHelper.getConnection(); Statement statement = connection.createStatement())
        {
            String sql = "SELECT * FROM Category";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                categories.add(createCategoryFromResultSet(resultSet));
            }
        } 
        return categories;
    }

    @Override
    public Category getCategoryByID(String categoryID) throws SQLException{
        String sql = "SELECT * FROM Category WHERE CatID=?";
        try (Connection connection = connectionHelper.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, categoryID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createCategoryFromResultSet(resultSet);
            }
        }
        return null;
    }

    public CategoryRepositoryImpl(ConnectionHelper connectionHelper) {
        this.connectionHelper = connectionHelper;
    }

    
    private Category createCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setID(resultSet.getString("CatID"));
        category.setName(resultSet.getString("CatName"));
        return category;
    }
}
