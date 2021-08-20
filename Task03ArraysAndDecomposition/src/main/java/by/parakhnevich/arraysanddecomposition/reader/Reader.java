package by.parakhnevich.arraysanddecomposition.reader;

import java.util.List;
import java.util.Scanner;


public interface Reader {
    public List<String> read(Scanner scan, boolean[] flags) throws Exception;
}
