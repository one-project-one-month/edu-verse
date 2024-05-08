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
public class User {

  @Id
  @Column(unique = true)
  private Integer id;

  @Column(length = 20, nullable = false)
  private String name;

  @Column(length = 50, nullable = false, unique = true)
  private String email;

  @Column(length = 50, nullable = false)
  private String password;

  @Column(length = 12, nullable = false, unique = true)
  private String phoneNumber;

  @Positive private int age;

  @Temporal(TemporalType.DATE)
  private LocalDate dob;

  @Enumerated(EnumType.STRING)
  private Gender gender;

  @Column(length = 80)
  private String address;

  @PrePersist
  public void generateUserId() {
    Random random = new Random();
    int r1 = random.nextInt(10, 99) + 1;
    int r2 = random.nextInt(10, 99) + 1;
    id = r1 * 100 + r2;
  }
}
