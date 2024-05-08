package dev.backend.eduverse.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Module {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(length = 25, nullable = false)
  private String name;

  @Column(length = 500)
  private String content;

  @Column(length = 20)
  private String duration;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id", referencedColumnName = "id")
  private Course course;
}
