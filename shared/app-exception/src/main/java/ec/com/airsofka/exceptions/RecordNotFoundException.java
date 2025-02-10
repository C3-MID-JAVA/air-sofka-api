package ec.com.airsofka.exceptions;

public class RecordNotFoundException  extends RuntimeException {
    public RecordNotFoundException(String message) {
        super(message);
    }
}