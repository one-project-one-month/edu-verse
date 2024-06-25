package dev.backend.eduverse.model;

import dev.backend.eduverse.util.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.Random;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", length = 20, nullable = false)
  private String name;

  @Column(name = "email", length = 50, nullable = false, unique = true)
  private String email;

  @Column(name = "password", length = 50, nullable = false)
  private String password;

  @Column(name = "phone_number", length = 12, nullable = false, unique = true)
  private String phoneNumber;

  @Positive
  @Column(name = "age")
  private int age;

  @Temporal(TemporalType.DATE)
  @Column(name = "dob")
  private LocalDate dob;

  @Enumerated(EnumType.STRING)
  @Column(name = "gender")
  private Gender gender;

  @Column(name = "address", length = 80)
  private String address;

  //    @JsonIgnore
  //    @ManyToMany(
  //            cascade = CascadeType.ALL,
  //            fetch = FetchType.LAZY
  //    )
  //    @JoinTable(
  //            name = "user_course",
  //            joinColumns = @JoinColumn(name = "user_id"),
  //            inverseJoinColumns = @JoinColumn(name = "course_id")
  //    )
  //    private List<Course> courseList;

  @PrePersist
  @PreUpdate
  public void generateUserId() {
    Random random = new Random();
    int r1 = random.nextInt();
    int r2 = random.nextInt();
    id = (long) r1 * r2;
  }
}
