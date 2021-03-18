package cz.cvut.fel.jankupat.AlkoApp.payload;

/**
 * The type Api response.
 */
public class ApiResponse {
    private boolean success;
    private String message;

    /**
     * Instantiates a new Api response.
     *
     * @param success the success
     * @param message the message
     */
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Is success boolean.
     *
     * @return the boolean
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets success.
     *
     * @param success the success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
