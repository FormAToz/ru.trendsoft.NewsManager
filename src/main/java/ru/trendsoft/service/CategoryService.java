package ru.trendsoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.trendsoft.entity.Category;
import ru.trendsoft.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService() {
    }

    public List<String> getAllCategoryNames() {
        return categoryRepository.findAllCategoryNames();
    }

    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByNameIgnoreCase(categoryName)
                .orElseThrow(() -> new IllegalArgumentException("Данной категории новостей не существует!"));
    }

    public boolean existsByName(String categoryName) {
        return categoryRepository.existsByNameIgnoreCase(categoryName);
    }
}
