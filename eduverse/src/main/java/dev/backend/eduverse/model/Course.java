package dev.backend.eduverse.model;

import dev.backend.eduverse.util.Level;
import jakarta.persistence.*;

import java.security.SecureRandom;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

  @Column(name = "name", length = 20, nullable = false)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "level")
    private Level level;

  @Column(name = "duration", length = 10, nullable = false)
  private String duration;

  @Column(name = "short_description", length = 100, nullable = false)
  private String shortDescription;

  @Column(name = "long_description", length = 500)
  private String longDescription;

  @Column(name = "created_at")
  private LocalDate createdAt;

    @Column(name = "status")
    private CourseStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id", referencedColumnName = "id")
    private Admin admin;

//    @JsonIgnore
//    @ManyToMany(
//            cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY
//    )
//    @JoinTable(
//            name = "user_course",
//            joinColumns = @JoinColumn(name = "course_id"),
//            inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//    private List<User> userList;
}


  @PrePersist
  private void setCourseId(){
    SecureRandom secureRandom = new SecureRandom();
    Long numberOne = secureRandom.nextLong(10, 99) + 1;
    Long numberTwo = secureRandom.nextLong(10, 99) + 1;
    id = numberOne * 100 + numberTwo;
  }
}
