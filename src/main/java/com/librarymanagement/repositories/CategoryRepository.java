package com.librarymanagement.repositories;

import com.librarymanagement.entities.Category;
import java.sql.SQLException;

import java.util.List;

public interface CategoryRepository {
    List<Category> getAllCategories() throws SQLException;
    Category getCategoryByID(String categoryID) throws SQLException;
}
