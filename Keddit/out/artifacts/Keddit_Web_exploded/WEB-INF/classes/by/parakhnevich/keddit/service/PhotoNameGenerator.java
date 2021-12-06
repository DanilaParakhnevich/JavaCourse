package by.parakhnevich.keddit.service;

import java.util.UUID;

public class PhotoNameGenerator {
    //part is name of user or a group , format is a format for ex .jpg or .jpeg
    public String generate(String part, String format) {
        return UUID.randomUUID() + part + format;
    }
}
