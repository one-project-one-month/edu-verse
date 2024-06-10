//package dev.backend.eduverse.controller;
//
//import java.util.List;
//
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import dev.backend.eduverse.dto.ModuleDto;
//import dev.backend.eduverse.service.ModuleService;
//import dev.backend.eduverse.util.response_template.ApiResponse;
//import dev.backend.eduverse.util.response_template.PageNumberResponse;
//import dev.backend.eduverse.util.response_template.ResponseUtil;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//
//@Tag(name = "CRUD REST APIs For Module")
//@RestController
//@RequestMapping("api/module")
//public class ModuleController {
//
//	private ModuleService moduleService;
//
//	private int pageSize = 10;
//
//	public ModuleController(ModuleService moduleService) {
//		this.moduleService = moduleService;
//	}
//
//	@Operation(summary = "Create Module For Course")
//	@PostMapping("/")
//	public ResponseEntity<ApiResponse<String>> createModule(@Valid @RequestBody ModuleDto moduleDto) {
//		try {
//			moduleService.createModule(moduleDto);
//			return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Module Created Successfuly.",
//					moduleDto.toString());
//		} catch (DataIntegrityViolationException e) {
//			return ResponseUtil.createErrorResponse(HttpStatus.BAD_REQUEST, "Failed To Create Module.", e.getMessage());
//		} catch (Exception e) {
//			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed To Create Module.",
//					e.getMessage());
//		}
//
//	}
//
//	@Operation(summary = "Update Module's Information")
//	@PutMapping("/{id}")
//	public ResponseEntity<ApiResponse<ModuleDto>> updateModule(@Valid @RequestBody ModuleDto moduleDto,
//			@PathVariable Long id) {
//		try {
//			ModuleDto updateModuleDto = moduleService.updateModule(moduleDto, id);
//			return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Module Updated Successfully.", updateModuleDto);
//		} catch (Exception e) {
//			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed To Update Module.", null);
//		}
//	}
//
//	@Operation(summary = "Delete Module By Id")
//	@DeleteMapping("/{id}")
//	public ResponseEntity<ApiResponse<String>> deleteModule(@PathVariable Long id) {
//		try {
//			moduleService.deleteModule(id);
//			return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Module Deletede Successfully.",
//					"Module Id :" + id);
//		} catch (EmptyResultDataAccessException e) {
//			return ResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, "Module Not Found.", null);
//		} catch (Exception e) {
//			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Failed To Delete Module.",
//					e.getMessage());
//		}
//	}
//
//	@Operation(summary = "Find All Module By Module Page")
//	@GetMapping
//	public ResponseEntity<PageNumberResponse<List<ModuleDto>>> getAllModulesByPage(
//			@RequestParam(name = "page", required = false, defaultValue = "1") int pageNo,
//			@RequestParam(required = false, defaultValue = "10") int limit) throws IllegalAccessException {
//
//		List<ModuleDto> moduleDtos = moduleService.getAllModulesByPagination(pageNo, limit);
//		return new ResponseEntity<>(new PageNumberResponse(pageNo, limit, moduleDtos), HttpStatus.OK);
//	}
//
//	@Operation(summary = "Find Module By Id")
//	@GetMapping("/{id}")
//	public ResponseEntity<ApiResponse<ModuleDto>> getById(@PathVariable Long id) {
//		try {
//			ModuleDto moduleDto = moduleService.getById(id);
//			return ResponseUtil.createSuccessResponse(HttpStatus.OK, "Module With Id" + id, moduleDto);
//		} catch (Exception e) {
//			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
//					"Fail To Retrieve With Module Id.", null);
//		}
//
//	}
//
//	@Operation(summary = "Find Module By CourseId")
//	@GetMapping("course/{courseId}")
//	public ResponseEntity<ApiResponse<List<ModuleDto>>> getByCourseId(@PathVariable Long courseId) {
//		try {
//			List<ModuleDto> moduleDtos = moduleService.getByCourseId(courseId);
//			return ResponseUtil.createSuccessResponse(HttpStatus.OK,
//					"Successfully Retrieved All Modules With Course Id." + courseId, moduleDtos);
//		} catch (Exception e) {
//			return ResponseUtil.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
//					"Failed To Retrieved All Modules With Course Id. ", null);
//		}
//
//	}
//
//}
