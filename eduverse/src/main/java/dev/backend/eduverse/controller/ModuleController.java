package dev.backend.eduverse.controller;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.backend.eduverse.dto.ModuleDto;
import dev.backend.eduverse.service.ModuleService;
import dev.backend.eduverse.util.ResponeTemplate.ApiResponse;
import dev.backend.eduverse.util.ResponeTemplate.ResponseUtil;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/module")
public class ModuleController {

	private ModuleService moduleService;

	public ModuleController(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	@PostMapping("/")
	public ResponseEntity<ApiResponse<String>> createModule(@Valid @RequestBody ModuleDto moduleDto) {
		try {
			moduleService.createModule(moduleDto);
			return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Module Created Successfuly.",
					moduleDto.toString());
		} catch (DataIntegrityViolationException e) {
			return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed To Create Module.", e.getMessage());
		} catch (Exception e) {
			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed To Create Module.",
					e.getMessage());
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<ModuleDto>> updateModule(@Valid @RequestBody ModuleDto moduleDto,
			@PathVariable Long id) {
		try {
			ModuleDto updateModuleDto = moduleService.updateModule(moduleDto, id);
			return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Module Updated Successfully.", updateModuleDto);
		} catch (Exception e) {
			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed To Update Module.", null);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<String>> deleteModule(@PathVariable Long id) {
		try {
			moduleService.deleteModule(id);
			return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Module Deletede Successfully.",
					"Module Id :" + id);
		} catch (EmptyResultDataAccessException e) {
			return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "Module Not Found.", null);
		} catch (Exception e) {
			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed To Delete Module.",
					e.getMessage());
		}
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<ModuleDto>>> getAllModules() {
		try {
			List<ModuleDto> moduleDtos = moduleService.getAllModules();
			return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Successfully Retrieved All Modules.", moduleDtos);
		} catch (Exception e) {
			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed To Retrieve All Modules.",
					null);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ModuleDto>> getById(@PathVariable Long id) {
		try {
			ModuleDto moduleDto = moduleService.getById(id);
			return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Module With Id" + id, moduleDto);
		} catch (Exception e) {
			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Fail To Retrieve With Module Id.", null);
		}

	}

	@GetMapping("{courseId}/modules")
	public ResponseEntity<ApiResponse<List<ModuleDto>>> getByCourseId(@PathVariable Long courseId) {
		try {
			List<ModuleDto> moduleDtos = moduleService.getByCourseId(courseId);
			return ResponseUtil.createSuccessResponse(HttpStatus.OK,
					"Successfully Retrieved All Modules With Course Id." + courseId, moduleDtos);
		} catch (Exception e) {
			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
					"Failed To Retrieved All Modules With Course Id. ", null);
		}

	}

}
