package psp.common.parameters;

public class ParametersException extends RuntimeException {

  public ParametersException() {
    super();
  }

  public ParametersException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public ParametersException(String message, Throwable cause) {
    super(message, cause);
  }

  public ParametersException(String message) {
    super(message);
  }

  public ParametersException(Throwable cause) {
    super(cause);
  }

}
