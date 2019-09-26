package http.supoort;

public class NonexistentUserIdException extends RuntimeException {
    public NonexistentUserIdException() {
    }

    public NonexistentUserIdException(String message) {
        super(message);
    }
}
