package by.parakhnevich.multithreadingmatrix.dao;

import java.io.File;

public class PathOfFileFor5Task {
    public String get(String name) {
        File file = new File("src/main/resources/files/" + name);
        return file.getAbsolutePath();
    }
}
