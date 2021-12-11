package by.parakhnevich.keddit.service;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PasswordServiceTest {
    PasswordService passwordService = new PasswordService();

    @Test
    public void testIsMatches() {
        String password = "222222222";
        String encryptedPassword = passwordService.encrypt(password);
        assert(passwordService.isMatches(password, encryptedPassword));
    }
}