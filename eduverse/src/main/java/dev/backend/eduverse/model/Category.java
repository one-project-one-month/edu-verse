package dev.backend.eduverse.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(length = 20, nullable = false)
  private String name;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "pathway_id", referencedColumnName = "id")
  private List<Pathway> pathwayList;
}
