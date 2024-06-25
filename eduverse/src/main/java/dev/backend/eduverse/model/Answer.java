package dev.backend.eduverse.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "answer")
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_correct", nullable = false)
    private boolean correct;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}
