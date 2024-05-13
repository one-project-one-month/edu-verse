package dev.backend.eduverse.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
