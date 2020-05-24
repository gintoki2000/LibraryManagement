package com.librarymanagement.repositories;

import com.librarymanagement.entities.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> getAllCategories();
    Category getCategoryByID(String categoryID);
}
