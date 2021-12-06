package by.parakhnevich.keddit.service;

import java.util.UUID;

/**
 * The type Photo name generator.
 */
public class PhotoNameGenerator {
    /**
     * Generate string.
     *
     * @param part   the part
     * @param format the format
     * @return the string
     */
    public String generate(String part, String format) {
        return UUID.randomUUID() + part + format;
    }
}
