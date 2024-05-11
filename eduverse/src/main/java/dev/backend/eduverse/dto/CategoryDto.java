package dev.backend.eduverse.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDto {

    @NotNull(message = "Category name is required")
    @Size(max = 20, message = "Category name is too long")
    private String name;
    private Long pathwayId;
}