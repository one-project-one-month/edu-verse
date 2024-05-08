package dev.backend.eduverse.model;

import dev.backend.eduverse.util.Level;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
  @Id private long id;

  @Column(length = 20, nullable = false)
  private String name;

  @Enumerated(EnumType.STRING)
  private Level level;

  @Column(length = 10, nullable = false)
  private String duration;

  @Column(length = 100, nullable = false)
  private String shortDescription;

  @Column(length = 500)
  private String longDescription;

  @Column(name = "created_at")
  private LocalDate createdAt;

  private boolean status;

  @ManyToMany
  @JoinTable(
      name = "course_category",
      joinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
  private List<Category> category;

  @ManyToOne private Admin admin;

  @ManyToOne
  @JoinColumn(name = "announcement_id", referencedColumnName = "id")
  private Announcement announcement;

  @OneToMany
  @JoinColumn(name = "course_details_id", referencedColumnName = "id")
  private List<CourseDetail> details;
}
