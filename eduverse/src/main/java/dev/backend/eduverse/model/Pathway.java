package dev.backend.eduverse.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pathway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String description;

    @ManyToOne
    private Category category;
}
