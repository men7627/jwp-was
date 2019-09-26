package http.supoort;

public class NotMatchPasswordException extends RuntimeException {
    public NotMatchPasswordException() {
    }

    public NotMatchPasswordException(String message) {
        super(message);
    }
}
