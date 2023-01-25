package com.petiveriaalliacea.taza.services;

import com.petiveriaalliacea.taza.entities.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getCategories();
    Category getCategory(Long id);
    Category addNewCategory(Category newCategory);
    Category editCategory(Long id, Category category);
    String deleteCategory(Long id);
}
