package dev.backend.eduverse.dto;

import java.time.LocalDate;

public class userCourseDTO {
  private long id;
  private LocalDate createdDate;
  private long userId;
  private long courseId;

  // Constructors, getters, and setters
  public userCourseDTO() {}

  public userCourseDTO(long id, LocalDate createdDate, long userId, long courseId) {
    this.id = id;
    this.createdDate = createdDate;
    this.userId = userId;
    this.courseId = courseId;
  }

  // Getters and Setters
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public long getCourseId() {
    return courseId;
  }

  public void setCourseId(long courseId) {
    this.courseId = courseId;
  }
}
