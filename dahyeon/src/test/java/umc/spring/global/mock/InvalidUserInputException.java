package umc.spring.global.mock;

import umc.spring.global.common.SkipNotification;

@SkipNotification(value = true)
public class InvalidUserInputException extends RuntimeException {
  public InvalidUserInputException(String message) {
    super(message);
  }
}
