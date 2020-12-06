package cz.cvut.fel.jankupat.AlkoApp.exception;

/**
 * The type Persistence exception.
 *
 * @author Patrik Jankuv
 * @created 8 /3/2020
 */
public class PersistenceException extends BaseException {

    /**
     * Instantiates a new Persistence exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Persistence exception.
     *
     * @param cause the cause
     */
    public PersistenceException(Throwable cause) {
        super(cause);
    }

}