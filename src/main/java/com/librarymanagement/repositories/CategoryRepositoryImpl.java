package com.librarymanagement.repositories;

import com.librarymanagement.entities.Category;
import com.librarymanagement.utils.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    private Connection connection;

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try (Statement statement = connection.createStatement())
        {
            String sql = "SELECT * FROM Category";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                categories.add(createCategoryFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category getCategoryByID(String categoryID) {
        String sql = "SELECT * FROM Category WHERE CatID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, categoryID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return createCategoryFromResultSet(resultSet);
            }
        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    @Inject(componentName = "connection")
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private Category createCategoryFromResultSet(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setID(resultSet.getString("CatID"));
        category.setName(resultSet.getString("CatName"));
        return category;
    }
}
