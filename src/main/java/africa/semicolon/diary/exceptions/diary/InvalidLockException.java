package africa.semicolon.diary.exceptions.diary;

public class InvalidLockException extends RuntimeException {
    public InvalidLockException(String message) {
        super(message);
    }
}
