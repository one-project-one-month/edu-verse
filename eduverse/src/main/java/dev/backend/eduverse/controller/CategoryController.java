//package dev.backend.eduverse.controller;
//
//import dev.backend.eduverse.dto.CategoryDto;
//import dev.backend.eduverse.service.impl.CategoryServiceImpl;
//import dev.backend.eduverse.util.response_template.ApiResponse;
//import dev.backend.eduverse.util.response_template.ResponseUtil;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@Tag(
//        name = "CRUD REST APIs for Category",
//        description =
//                "CRUD REST APIs - Create Category, Update Category, Get Category By Id, Get All Category,"
//                        + " Delete Category, Update Category")
//@RestController
//@RequestMapping("/api/category")
//public class CategoryController {
//
//    private final Logger logger = LoggerFactory.getLogger(CategoryController.class);
//    private final CategoryServiceImpl categoryService;
//
//    @Autowired
//    public CategoryController(CategoryServiceImpl categoryService) {
//        this.categoryService = categoryService;
//    }
//
//    @PostMapping("/")
//    @Operation(
//            summary = "Create new category",
//            tags = {"Create Category"})
//    public ResponseEntity<ApiResponse<String>> createCategory(
//            @Valid @RequestBody CategoryDto categoryDto) {
//        try {
//            categoryService.createNewCategory(categoryDto);
//            return ResponseUtil.createSuccessResponse(
//                    HttpStatus.OK, "Category Created successfully", categoryDto.toString());
//        } catch (DataIntegrityViolationException e) {
//            return ResponseUtil.createErrorResponse(
//                    HttpStatus.BAD_REQUEST, "Failed to create category", e.getMessage());
//        } catch (Exception e) {
//            return ResponseUtil.createErrorResponse(
//                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create category", e.getMessage());
//        }
//    }
//
//    @GetMapping
//    @Operation(
//            summary = "Get all category",
//            tags = {"Get all Category"})
//    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategory() {
//        try {
//            List<CategoryDto> categories = categoryService.getAllCategory();
//            return ResponseUtil.createSuccessResponse(
//                    HttpStatus.OK, " Successfully get categoreis", categories);
//        } catch (Exception e) {
//            return ResponseUtil.createErrorResponse(
//                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch category", null);
//        }
//    }
//
//    @GetMapping("/{categoryId}")
//    @Operation(
//            summary = "Get  category",
//            tags = {"Get Category"})
//    public ResponseEntity<ApiResponse<CategoryDto>> getCategory(@PathVariable Long categoryId) {
//        try {
//            CategoryDto categoryDto = categoryService.getCategory(categoryId);
//            return ResponseUtil.createSuccessResponse(
//                    HttpStatus.OK, "Get Category with ID" + categoryId, categoryDto);
//        } catch (Exception e) {
//            return ResponseUtil.createErrorResponse(
//                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to fetch category", null);
//        }
//    }
//
//    @PutMapping("/{categoryId}")
//    @Operation(
//            summary = "Update  category",
//            tags = {"Update Category"})
//    public ResponseEntity<ApiResponse<CategoryDto>> UpdateCategory(
//            @Valid @RequestBody CategoryDto categoryDto, @PathVariable Long categoryId) {
//        try {
//            CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, categoryId);
//            return ResponseUtil.createSuccessResponse(
//                    HttpStatus.OK, "Updated successfully", updatedCategory);
//        } catch (Exception e) {
//            return ResponseUtil.createErrorResponse(
//                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to Update category", null);
//        }
//    }
//
//    @DeleteMapping("/{categoryId}")
//    @Operation(
//            summary = "Delete category by id ",
//            tags = {"Delete Category by ID"})
//    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable Long categoryId) {
//        try {
//            categoryService.deleteCategory(categoryId);
//            return ResponseUtil.createSuccessResponse(
//                    HttpStatus.OK, "Category Deleted successfully", "Id " + categoryId);
//        } catch (EmptyResultDataAccessException e) {
//            return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "Category not found", null);
//        } catch (Exception e) {
//            return ResponseUtil.createErrorResponse(
//                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete Category", e.getMessage());
//        }
//    }
//}
