package cz.cvut.fel.jankupat.AlkoApp.exception;

/**
 * The type Base exception.
 *
 * @author Patrik Jankuv
 * @created 8 /3/2020
 */
public class BaseException extends RuntimeException {

    /**
     * Instantiates a new Base exception.
     */
    public BaseException() {
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param message the message
     */
    public BaseException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Base exception.
     *
     * @param cause the cause
     */
    public BaseException(Throwable cause) {
        super(cause);
    }

}