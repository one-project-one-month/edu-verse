package dev.backend.eduverse.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "question")
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "question", nullable = false)
    private String question;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "exam_id", referencedColumnName = "id")
    private Exam exam;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id",referencedColumnName = "id")
    private List<Answer> answers;
}
