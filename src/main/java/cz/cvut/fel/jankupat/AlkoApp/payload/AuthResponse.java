package cz.cvut.fel.jankupat.AlkoApp.payload;

/**
 * The type Auth response.
 */
public class AuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    /**
     * Instantiates a new Auth response.
     *
     * @param accessToken the access token
     */
    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Gets access token.
     *
     * @return the access token
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Sets access token.
     *
     * @param accessToken the access token
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Gets token type.
     *
     * @return the token type
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * Sets token type.
     *
     * @param tokenType the token type
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
