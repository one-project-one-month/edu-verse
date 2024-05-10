package dev.backend.eduverse.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCourse {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Temporal(TemporalType.DATE)
  private LocalDate createdDate;

  @ManyToOne private User user;

  @ManyToOne private Course course;
}
