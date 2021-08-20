package by.parakhnevich.arraysanddecomposition.reader;

import java.util.List;
import java.util.Scanner;

public class ReadTwoMatrices implements Reader{
    @Override
    public List<String> read(Scanner scan, boolean[] flags) throws Exception {
        ReadMatrix readMatrix = new ReadMatrix();
        readMatrix.flagOff();
        List<String> list = readMatrix.read(scan, flags);
        List<String> list2  = readMatrix.read(scan, flags);
        list.addAll(list2);
        return list;
    }
}

