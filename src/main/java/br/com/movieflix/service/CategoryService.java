package br.com.movieflix.service;

import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryResponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.mapper.CategoryMapper;
import br.com.movieflix.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        ;
    }

    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryMapper::toCategoryResponse)
                .toList();
    }

    public CategoryResponse saveCategory(CategoryRequest request) {
        Category category = CategoryMapper.toModel(request);
        Category categorySaved = categoryRepository.save(category);
        return CategoryMapper.toCategoryResponse(categorySaved);
    }

    public Optional<CategoryResponse> findById(Long id) {
        return categoryRepository.findById(id)
                .map(CategoryMapper::toCategoryResponse);
    }

    public boolean deleteById(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
