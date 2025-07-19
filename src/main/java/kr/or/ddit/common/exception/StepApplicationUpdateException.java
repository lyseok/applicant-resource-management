package kr.or.ddit.common.exception;

public class StepApplicationUpdateException extends RuntimeException{
	public StepApplicationUpdateException() {
        super();
    }
    public StepApplicationUpdateException(String message) {
        super(message);
    }
    public StepApplicationUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
    public StepApplicationUpdateException(Throwable cause) {
        super(cause);
    }
}
