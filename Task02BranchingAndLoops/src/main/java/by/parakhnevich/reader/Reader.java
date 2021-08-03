package by.parakhnevich.reader;

import java.util.List;
import java.util.Scanner;

public interface Reader {
    public List<Number> read(Scanner scan,boolean isFromFile) throws Exception;
}
