package dev.backend.eduverse.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(length = 20, nullable = false)
  private String title;

  @Column(length = 500)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  private Course course;

  @ManyToOne(fetch = FetchType.LAZY)
  private Admin admin;
}
