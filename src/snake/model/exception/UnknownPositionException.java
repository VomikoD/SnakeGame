package snake.model.exception;

public class UnknownPositionException extends Exception {
    public UnknownPositionException() {
        super("Неизвестная позиция");
    }
}
