package vn.edu.hcmut.exception;

/**
 * @author Phuoc Cao
 */
public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(String message) {
        super(message);
    }
}