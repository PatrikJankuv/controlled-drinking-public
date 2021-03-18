package cz.cvut.fel.jankupat.AlkoApp.security.oauth2.user;

import cz.cvut.fel.jankupat.AlkoApp.exception.OAuth2AuthenticationProcessingException;
import cz.cvut.fel.jankupat.AlkoApp.model.AuthProvider;

import java.util.Map;

/**
 * The type O auth 2 user info factory.
 */
public class OAuth2UserInfoFactory {

    /**
     * Gets o auth 2 user info.
     *
     * @param registrationId the registration id
     * @param attributes     the attributes
     * @return the o auth 2 user info
     */
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else if (registrationId.equalsIgnoreCase(AuthProvider.facebook.toString())) {
            return new FacebookOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
