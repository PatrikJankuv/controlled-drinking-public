package cz.cvut.fel.jankupat.AlkoApp.exception;

import cz.cvut.fel.jankupat.AlkoApp.model.BaseEntity;

/**
 * @author Patrik Jankuv
 * @created 8/3/2020
 */
public class NotFoundException extends BaseException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public static NotFoundException create(String resourceName, Object identifier) {
        return new NotFoundException(resourceName + " identified by " + identifier + " not found.");
    }
}