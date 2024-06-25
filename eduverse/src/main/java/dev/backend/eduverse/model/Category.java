package dev.backend.eduverse.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "category")
public class Category {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", length = 20, nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "pathway_id", referencedColumnName = "id")
  private Pathway pathway;

  //    @JsonIgnore
  //    @ManyToMany(
  //            cascade = CascadeType.ALL,
  //            fetch = FetchType.LAZY
  //    )
  //    @JoinTable(
  //            name = "course_category",
  //            joinColumns = @JoinColumn(name = "category_id"),
  //            inverseJoinColumns = @JoinColumn(name = "course_id")
  //    )
  //    private List<Course> courseList;
}
