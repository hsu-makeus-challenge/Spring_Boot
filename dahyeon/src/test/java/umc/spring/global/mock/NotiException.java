package umc.spring.global.mock;

import umc.spring.global.common.SkipNotification;

@SkipNotification(value = false)
public class NotiException extends RuntimeException {
  public NotiException(String message) {
    super(message);
  }
}
