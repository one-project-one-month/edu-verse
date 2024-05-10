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
  @Column(name = "id")
    private long id;

  @Column(name = "title", length = 20, nullable = false)
  private String title;

  @Column(name = "content", length = 500)
  private String content;


    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;
}
