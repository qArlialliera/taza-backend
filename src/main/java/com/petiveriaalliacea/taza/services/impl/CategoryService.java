package com.petiveriaalliacea.taza.services.impl;

import com.petiveriaalliacea.taza.entities.Category;
import com.petiveriaalliacea.taza.repositories.CategoryRepository;
import com.petiveriaalliacea.taza.services.ICategoryService;
import com.petiveriaalliacea.taza.utils.StringUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category addNewCategory(Category newCategory) {
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category editCategory(Long id, Category dto) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            if (!StringUtils.isEmpty(dto.getName())) {
                category.get().setName(dto.getName());
            }
            if (!StringUtils.isEmpty(dto.getNameRu())) {
                category.get().setNameRu(dto.getNameRu());
            }
            if (!StringUtils.isEmpty(dto.getNameKz())) {
                category.get().setNameKz(dto.getNameKz());
            }
        }
        return categoryRepository.save(category.get());
    }

    @Override
    public String deleteCategory(Long id) {
        if(categoryRepository.findById(id).isPresent()) {
            categoryRepository.deleteById(id);
            return "Deleted Successfully!";
        }
        return "Category not found!";
    }
}
