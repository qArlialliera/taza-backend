package com.petiveriaalliacea.taza.rest;

import com.petiveriaalliacea.taza.entities.Category;
import com.petiveriaalliacea.taza.services.impl.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
@CrossOrigin(allowedHeaders = "*", origins = "*")

public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok().body(categoryService.getCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategory(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addNewCategory(@RequestBody Category category) {
        return ResponseEntity.ok(categoryService.addNewCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> editCategory(@RequestBody Category company, @PathVariable Long id) {
        return ResponseEntity.ok(categoryService.editCategory(id, company));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity deleteCategory(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.deleteCategory(id));
    }

}