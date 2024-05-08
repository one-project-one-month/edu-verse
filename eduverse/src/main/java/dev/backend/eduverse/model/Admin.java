package dev.backend.eduverse.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.Set;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Admin {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(length = 50, nullable = false)
  private String username;

  @Column(length = 50, nullable = false)
  private String password;

  @Column(length = 50, nullable = false, unique = true)
  private String email;

  @Column(length = 12, unique = true)
  private String phoneNumber;

  private boolean status;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "id", cascade = CascadeType.ALL)
  private List<AdminRole> adminRole;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "course_id", referencedColumnName = "id")
  private Set<Course> courseList;

  @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "course_details_id", referencedColumnName = "id")
  private List<CourseDetail> courseDetailList;
}
