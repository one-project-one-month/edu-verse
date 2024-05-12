package dev.backend.eduverse.service;

import dev.backend.eduverse.dto.CategoryDto;
import java.util.List;

public interface CategoryService {

  List<CategoryDto> getAllCategory();

  CategoryDto getCategory(Long id);

  CategoryDto createNewCategory(CategoryDto categoryDto);

  CategoryDto updateCategory(CategoryDto categoryDto, Long id);

  void deleteCategory(Long id);
}
