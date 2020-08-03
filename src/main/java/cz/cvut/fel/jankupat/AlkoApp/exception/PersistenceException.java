package cz.cvut.fel.jankupat.AlkoApp.exception;

/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */
public class PersistenceException extends BaseException {

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistenceException(Throwable cause) {
        super(cause);
    }

}