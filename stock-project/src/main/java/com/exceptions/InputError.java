package com.exceptions;

public class InputError extends Exception {
    public InputError(String message) {
        super(message);
    }

  public InputError(String message, Throwable cause) {
    super(message, cause);
  }

  public InputError(Throwable cause) {
    super(cause);
  }

  public InputError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public InputError() {
  }
}
