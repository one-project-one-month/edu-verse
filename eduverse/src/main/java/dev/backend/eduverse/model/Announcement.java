package dev.backend.eduverse.model;

import dev.backend.eduverse.util.NotificationType;
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
public class Announcement {
  // Suggestion : Decide whether we should add a new column with value 'System Level' and 'Course
  // Level'.
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(length = 30, nullable = false)
  private String title;

  @Column(length = 100)
  private String content;

  @Temporal(TemporalType.DATE)
  private LocalDate createAt;

  @Enumerated(EnumType.STRING)
  @Column(name = "noti_type")
  private NotificationType notificationType;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "admin_id", referencedColumnName = "id")
  private Admin admin;

  @OneToMany(mappedBy = "announcement")
  private List<Course> course;

  @PrePersist
  private void setCreateAt() {
    createAt = LocalDate.now();
  }

}
