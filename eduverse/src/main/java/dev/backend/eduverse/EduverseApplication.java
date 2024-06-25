package dev.backend.eduverse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
public class EduverseApplication {

  public static void main(String[] args) {
    SpringApplication.run(EduverseApplication.class, args);
  }
}
