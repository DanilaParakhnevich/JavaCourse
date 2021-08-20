package by.parakhnevich.arraysanddecomposition.reader;

import by.parakhnevich.arraysanddecomposition.view.locale.LocaleSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadNameOfFile implements Reader{

    @Override
    public List<String> read(Scanner scan, boolean[] flags) throws Exception {
        System.out.println(LocaleSingleton.getResourceBundle().getString("READ_FILE"));
        List<String> list = new ArrayList<>();
        list.add(scan.next());
        return list;
    }
}
