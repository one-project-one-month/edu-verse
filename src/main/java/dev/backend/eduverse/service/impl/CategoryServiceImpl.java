package dev.backend.eduverse.service.impl;

import dev.backend.eduverse.dto.CategoryDto;
import dev.backend.eduverse.exception.NameAlreadyExistException;
import dev.backend.eduverse.exception.ResourceNotFoundException;
import dev.backend.eduverse.model.Category;
import dev.backend.eduverse.model.Pathway;
import dev.backend.eduverse.repository.CategoryRepository;
import dev.backend.eduverse.repository.PathwayRepository;
import dev.backend.eduverse.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;

  private final ModelMapper modelMapper = new ModelMapper();

  private final PathwayRepository pathwayRepository;

  private final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

  /** Constructor Injection */
  @Autowired
  public CategoryServiceImpl(
      CategoryRepository categoryRepository, PathwayRepository pathwayRepository) {
    this.categoryRepository = categoryRepository;
    this.pathwayRepository = pathwayRepository;
  }

  @Override
  public List<CategoryDto> getAllCategory() {
    List<Category> categories = categoryRepository.findAll();
    return categories.stream()
        .map(category -> modelMapper.map(category, CategoryDto.class))
        .collect(Collectors.toList());
  }

  @Override
  public CategoryDto getCategory(Long id) {
    Category category =
        categoryRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Category", "ID", id));
    return modelMapper.map(category, CategoryDto.class);
  }

  @Override
  public CategoryDto createNewCategory(CategoryDto categoryDto) {
    logger.info("Creating Category");
    Pathway pathway =
        pathwayRepository
            .findById(categoryDto.getPathwayId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Pathway", "id", categoryDto.getPathwayId()));

    Optional<Category> categoryOptional = categoryRepository.findByName(categoryDto.getName());
    if (categoryOptional.isPresent()) {
      throw new NameAlreadyExistException("Name already exit for that category");
    }

    Category category = modelMapper.map(categoryDto, Category.class);
    category.setPathway(pathway);
    Category savedCategory = categoryRepository.save(category);

    logger.info("Success created category");
    return modelMapper.map(savedCategory, CategoryDto.class);
  }

  @Override
  public CategoryDto updateCategory(CategoryDto categoryDto, Long id) {
    logger.info("Updating category");

    Pathway pathway =
        pathwayRepository
            .findById(categoryDto.getPathwayId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Pathway", "id", categoryDto.getPathwayId()));

    Optional<Category> existingCategoryOptional = categoryRepository.findById(id);
    Category existingCategory =
        existingCategoryOptional.orElseThrow(
            () -> new EntityNotFoundException("Category not found with ID " + id));

    existingCategory.setName(categoryDto.getName());
    existingCategory.setPathway(pathway);

    categoryRepository.save(existingCategory);
    logger.info("Category saved successfully");

    return modelMapper.map(existingCategory, CategoryDto.class);
  }

  @Override
  public void deleteCategory(Long id) {
    logger.info("category ID to delete :");
    try {
      categoryRepository.deleteById(id);
      logger.info("Category Delete successfully");
    } catch (EmptyResultDataAccessException e) {
      logger.error("Category not found for deletion with ID: {}", id);
      throw new EntityNotFoundException("Category not found with ID: " + id);
    }
  }
}
