package by.parakhnevich.task08xmlparsing.service;

import java.io.File;

public class FileInResources {
    public File getFile(String fileName) {
        return new File("J:/Projects/JavaCourse/Task08XMLParsing/src/main/resources/"
                + fileName);
    }
}
