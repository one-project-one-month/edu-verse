package dev.backend.eduverse.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Table(name = "token")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "is_admin")
    private boolean isAdmin;

    public Token(String token, boolean isAdmin) {
        this.token = token;
        this.isAdmin = isAdmin;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
