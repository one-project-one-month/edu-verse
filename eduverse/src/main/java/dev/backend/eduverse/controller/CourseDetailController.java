/**
 * @Author : Kyaw Zaw Htet
 * @Date : 5/11/2024
 * @Time : 11:09 PM
 * @Project_Name : eduverse
 */
package dev.backend.eduverse.controller;

import dev.backend.eduverse.dto.CourseDetailDto;
import dev.backend.eduverse.service.CourseDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD REST APIs for CourseDetails",
        description = "CRUD REST APIs - Create CourseDetail, Update CourseDetail, Get CourseDetail By Id, Get All CourseDetails, Delete CourseDetail"
)

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/coursedetails")
public class CourseDetailController {

    private CourseDetailService courseDetailService;

    @Operation(
            summary = "Get All CourseDetail REST API",
            description = "Get All CourseDetail REST API is used to get all the CourseDetails from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping
    public ResponseEntity<List<CourseDetailDto>> getAllCourseDetails(){

        List<CourseDetailDto> courseDetails = courseDetailService.getAllCourseDetails();
        return new ResponseEntity<>(courseDetails, HttpStatus.OK);
    }

    @Operation(
            summary = "Get CourseDetail By Id REST API",
            description = "Get CourseDetail By Id REST API is used to get a single CourseDetail from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @GetMapping("{id}")
    public ResponseEntity<CourseDetailDto> getCourseDetailById(@PathVariable("id") Long id){

        CourseDetailDto courseDetail = courseDetailService.getCourseDetailById(id);
        return new ResponseEntity<>(courseDetail, HttpStatus.OK);
    }

    @Operation(
            summary = "Create CourseDetail REST API",
            description = "Create CourseDetail REST API is used to save CourseDetail in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    @PostMapping("/create")
    public ResponseEntity<CourseDetailDto> createCourseDetail(@Valid @RequestBody CourseDetailDto courseDetail){

        CourseDetailDto createCourseDetail = courseDetailService.createCourseDetail(courseDetail);
        return new ResponseEntity<>(createCourseDetail, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update CourseDetail REST API",
            description = "Update CourseDetail REST API is used to update a particular CourseDetail in the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @PutMapping("{id}")
    public ResponseEntity<CourseDetailDto> updateCourseDetail(@PathVariable("id") Long id, @RequestBody @Valid CourseDetailDto courseDetail) {

        courseDetail.setId(id);
        CourseDetailDto updatedCourseDetail = courseDetailService.updateCourseDetail(courseDetail);
        return new ResponseEntity<>(updatedCourseDetail, HttpStatus.OK);
    }

    @Operation(
            summary = "Delete CourseDetail REST API",
            description = "Delete CourseDetail REST API is used to delete a particular CourseDetail from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCourseDetail(@PathVariable("id") Long id){

        courseDetailService.deleteCourseDetail(id);
        return new ResponseEntity<>("CourseDetail successfully deleted!", HttpStatus.OK);
    }
}
