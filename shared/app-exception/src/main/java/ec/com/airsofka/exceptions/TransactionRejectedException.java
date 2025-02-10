package ec.com.airsofka.exceptions;

public class TransactionRejectedException  extends RuntimeException {
    public TransactionRejectedException(String message) {
        super(message);
    }
}