package com.example.logback_with_elk.api;

import org.springframework.stereotype.Service;

@Service
public class DishService {

  private final static String REPLY_IS_DISH_IMPORTANT = "YES";

  public String getAnswer() {
    return REPLY_IS_DISH_IMPORTANT;
  }

  public void no() {
    throw new IllegalArgumentException("Impossible");
  }
}
