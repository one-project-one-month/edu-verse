package dev.backend.eduverse.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

  private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

  @Tag(name = "get", description = "GET method of Welcome API")
  @GetMapping("/")
  public String welcome(
      @RequestParam(name = "name", required = false, defaultValue = "World") String name) {

    // logging level example
    logger.debug("Debug level - Hello Log back");

    logger.info("Info level - Hello Log back");

    logger.error("Error level - Hello Log back");

    return "Hello " + name;
  }
}
