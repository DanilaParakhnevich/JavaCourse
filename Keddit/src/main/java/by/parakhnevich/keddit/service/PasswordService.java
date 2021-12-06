package by.parakhnevich.keddit.service;

import org.mindrot.jbcrypt.BCrypt;

/**
 * PasswordService may encrypt password and compare encrypted password with just password
 */
public class PasswordService {
    /**
     * Encrypt string.
     *
     * @param password the password
     * @return the string
     */
    public String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    /**
     * Is matches boolean.
     *
     * @param password          the password
     * @param encryptedPassword the encrypted password
     * @return the boolean
     */
    public boolean isMatches (String password, String encryptedPassword) {
        return BCrypt.checkpw(password, encryptedPassword);
    }
}
