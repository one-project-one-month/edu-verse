package dev.backend.eduverse.model;

import dev.backend.eduverse.util.NotificationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Announcement {
    // Suggestion : Decide whether we should add a new column with value 'System Level' and 'Course Level'.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 30, nullable = false)
    private String title;

    @Column(name = "content", length = 100)
    private String content;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private LocalDate createdAt;

  @Enumerated(EnumType.STRING)
  @Column(name = "noti_type")
  private NotificationType notificationType;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "admin_id", referencedColumnName = "id")
  private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
}
