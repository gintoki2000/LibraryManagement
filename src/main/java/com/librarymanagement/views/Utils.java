/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.librarymanagement.views;

import com.librarymanagement.entities.Category;
import com.librarymanagement.repositories.CategoryRepository;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ngotrung
 */
public class Utils {
    public static String[] getCategoryIDs(CategoryRepository categoryRepository) throws SQLException {
        List<Category> categories = categoryRepository.getAllCategories();
        String[] catIDs = new String[categories.size()];
        for (int i = 0; i < catIDs.length; ++i) {
            catIDs[i] = categories.get(i).getID();
        }
        return catIDs;
    }  
    
}
