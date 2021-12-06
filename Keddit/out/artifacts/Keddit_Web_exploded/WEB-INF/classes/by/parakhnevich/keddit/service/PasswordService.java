package by.parakhnevich.keddit.service;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordService {
    public String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public boolean isMatches (String password, String encryptedPassword) {
        return BCrypt.checkpw(password, encryptedPassword);
    }
}
