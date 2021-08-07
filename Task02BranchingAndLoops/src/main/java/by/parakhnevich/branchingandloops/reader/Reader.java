package by.parakhnevich.branchingandloops.reader;

import java.util.List;
import java.util.Scanner;

public interface Reader {
    public List<String> read(Scanner scan, boolean isFromFile)throws Exception;
}
