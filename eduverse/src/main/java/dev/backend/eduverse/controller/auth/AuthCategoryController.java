package dev.backend.eduverse.controller.auth;

import dev.backend.eduverse.dto.CategoryDto;
import dev.backend.eduverse.service.CategoryService;
import dev.backend.eduverse.util.response_template.ApiResponse;
import dev.backend.eduverse.util.response_template.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthCategoryController {
    private CategoryService categoryService;

    @PostMapping("/admin/category")
    @Operation(summary = "Create new category", tags = {"Create Category"})
    public ResponseEntity<ApiResponse<String>> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        try {
            categoryService.createNewCategory(categoryDto);
            return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Category Created successfully",
                    categoryDto.toString());
        } catch (DataIntegrityViolationException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed to create category",
                    e.getMessage());
        } catch (Exception e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create category",
                    e.getMessage());
        }
    }


    @DeleteMapping("/admin/category/{categoryId}")
    @Operation(summary = "Delete category by id ", tags = {"Delete Category by ID"})
    public ResponseEntity<ApiResponse<String>> deleteCategory(@PathVariable Long categoryId) {
        try {
            categoryService.deleteCategory(categoryId);
            return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Category Deleted successfully",
                    "Id " + categoryId);
        } catch (EmptyResultDataAccessException e) {
            return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "Category not found", null);
        } catch (Exception e) {
            return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to delete Category",
                    e.getMessage());
        }
    }
}
