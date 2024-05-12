package dev.backend.eduverse.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "admin")
public class Admin {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  @Column(name = "user_name", length = 50, nullable = false)
  private String username;

  @Column(name = "password", length = 68, nullable = false)
  private String password;

  @Column(name = "email", length = 50, nullable = false, unique = true)
  private String email;

  @Column(name = "phone_number", length = 12, unique = true)
  private String phoneNumber;

  @Column(name = "status")
  private boolean status;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "role_id", referencedColumnName = "id")
  private AdminRole adminRole;
}
