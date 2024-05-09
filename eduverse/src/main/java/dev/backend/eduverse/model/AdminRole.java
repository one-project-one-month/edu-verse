package dev.backend.eduverse.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "admin_role")
public class AdminRole {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(length = 4, nullable = false)
  private String code;

  @Column(length = 20)
  private String roleName;

  @ManyToOne
  @JoinColumn(name = "admin_id", referencedColumnName = "id")
  private Admin admin;



}
