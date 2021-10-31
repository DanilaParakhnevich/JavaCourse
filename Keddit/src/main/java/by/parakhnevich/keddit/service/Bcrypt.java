package by.parakhnevich.keddit.service;

import org.mindrot.jbcrypt.BCrypt;

public class Bcrypt {
    public String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public Boolean isMatch(String password, String encryptedPassword) {
        return BCrypt.checkpw(password, encryptedPassword);
    }
}
