package dev.backend.eduverse.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@ComponentScan({
	"dev.backend.eduverse"
})
@Configuration
public class BeanConfiguration {

  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
