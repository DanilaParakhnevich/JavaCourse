package by.parakhnevich.informationhandling.dao;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Reader {
    private final String absolutePath;

    public Reader(String name) {
        absolutePath = getPathToFile(name);
    }

    public String readAll() throws IOException {
        List<String> result = Files.lines(Paths.get(absolutePath)).
                collect(Collectors.toList());
        StringBuilder builder = new StringBuilder();
        result.forEach(element -> builder.append(element).append('\n'));
        return builder.toString();
    }

    private String getPathToFile(String name) {
        File file = new File("src/main/resources/files/" + name);
        return file.getAbsolutePath();
    }
}
