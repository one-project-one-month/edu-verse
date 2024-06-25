package dev.backend.eduverse.model;

import dev.backend.eduverse.util.CourseStatus;
import dev.backend.eduverse.util.Level;
import jakarta.persistence.*;

import java.time.LocalDate;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "course")
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    @Column(name = "image")
    private String image;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CourseStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
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
