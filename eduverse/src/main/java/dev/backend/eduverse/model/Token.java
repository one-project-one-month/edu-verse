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

    @Column(name = "user_id")
    private Long user_id;

    public Token(String token, boolean isAdmin, Long user_id) {
        this.token = token;
        this.isAdmin = isAdmin;
        this.user_id = user_id;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }
}
