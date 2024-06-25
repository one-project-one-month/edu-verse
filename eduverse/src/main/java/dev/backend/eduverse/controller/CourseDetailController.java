///**
// * @Author : Kyaw Zaw Htet @Date : 5/11/2024 @Time : 11:09 PM @Project_Name : eduverse
// */
//package dev.backend.eduverse.controller;
//
//import dev.backend.eduverse.dto.AnnouncementDto;
//import dev.backend.eduverse.dto.CourseDetailDto;
//import dev.backend.eduverse.service.CourseDetailService;
//import dev.backend.eduverse.util.response_template.PageNumberResponse;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.validation.Valid;
//import java.util.List;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@Tag(
//    name = "CRUD REST APIs for CourseDetails",
//    description =
//        "CRUD REST APIs - Create CourseDetail, Update CourseDetail, Get CourseDetail By Id, Get All"
//            + " CourseDetails, Delete CourseDetail")
//@RestController
//@AllArgsConstructor
//@RequestMapping("api/coursedetail")
//public class CourseDetailController {
//
//  private CourseDetailService courseDetailService;
//
//  private final int PageSize = 10;
//
//  @Operation(
//      summary = "Get All CourseDetail",
//      description =
//          "Get All CourseDetail REST API is used to get all the CourseDetails from the database")
//  @ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//
//  @GetMapping("")
//  public ResponseEntity<PageNumberResponse<CourseDetailDto>> getAllCourseDetails(
//          @RequestParam(value = "page", required = false, defaultValue = "1") int pageNo,
//          @RequestParam(value = "limit", required = false, defaultValue = "10") int limit
//  ) {
//
//    List<CourseDetailDto> announcements = courseDetailService.paginate(pageNo, limit);
//
//    return new ResponseEntity<>(
//            new PageNumberResponse(pageNo, limit, announcements),
//            HttpStatus.OK
//    );
//  }
//
////  @GetMapping
////  public ResponseEntity<List<CourseDetailDto>> getAllCourseDetails() {
////
////    List<CourseDetailDto> courseDetails = courseDetailService.getAllCourseDetails();
////    return new ResponseEntity<>(courseDetails, HttpStatus.OK);
////  }
//
//  @Operation(
//      summary = "Get CourseDetail By Id",
//      description =
//          "Get CourseDetail By Id REST API is used to get a single CourseDetail from the database")
//  @ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//  @GetMapping("/{coursedetailId}")
//  public ResponseEntity<CourseDetailDto> getCourseDetailById(@PathVariable("coursedetailId") Long id) {
//
//    CourseDetailDto courseDetail = courseDetailService.getCourseDetailById(id);
//    return new ResponseEntity<>(courseDetail, HttpStatus.OK);
//  }
//
//  @Operation(
//      summary = "Create CourseDetail",
//      description = "Create CourseDetail REST API is used to save CourseDetail in a database")
//  @ApiResponse(responseCode = "201", description = "HTTP Status 201 CREATED")
//  @PostMapping("/")
//  public ResponseEntity<CourseDetailDto> createCourseDetail(
//      @Valid @RequestBody CourseDetailDto courseDetail) {
//
//    CourseDetailDto createCourseDetail = courseDetailService.createCourseDetail(courseDetail);
//    return new ResponseEntity<>(createCourseDetail, HttpStatus.CREATED);
//  }
//
//  @Operation(
//      summary = "Update CourseDetail",
//      description =
//          "Update CourseDetail REST API is used to update a particular CourseDetail in the"
//              + " database")
//  @ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//  @PutMapping("/{coursedetailId}")
//  public ResponseEntity<CourseDetailDto> updateCourseDetail(
//      @PathVariable("coursedetailId") Long id, @RequestBody @Valid CourseDetailDto courseDetail) {
//
//    courseDetail.setId(id);
//    CourseDetailDto updatedCourseDetail = courseDetailService.updateCourseDetail(courseDetail);
//    return new ResponseEntity<>(updatedCourseDetail, HttpStatus.OK);
//  }
//
//  @Operation(
//      summary = "Delete CourseDetail",
//      description =
//          "Delete CourseDetail REST API is used to delete a particular CourseDetail from the"
//              + " database")
//  @ApiResponse(responseCode = "200", description = "HTTP Status 200 SUCCESS")
//  @DeleteMapping("/{coursedetailId}")
//  public ResponseEntity<String> deleteCourseDetail(@PathVariable("coursedetailId") Long id) {
//
//    courseDetailService.deleteCourseDetail(id);
//    return new ResponseEntity<>("CourseDetail successfully deleted!", HttpStatus.OK);
//  }
//}
