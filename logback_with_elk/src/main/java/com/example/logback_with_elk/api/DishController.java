package com.example.logback_with_elk.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishController {

  private final DishService dishService;

  @GetMapping("/is_important")
  public ResponseEntity<String> reply() {
    String answer = dishService.getAnswer();
    return ResponseEntity.ok(answer);
  }

  @GetMapping("/not_important")
  public void no() {
    dishService.no();
  }
}
