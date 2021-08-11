package by.parakhnevich.arraysanddecomposition.runner;

import by.parakhnevich.arraysanddecomposition.entity.Array;
import by.parakhnevich.arraysanddecomposition.utilarrays.*;
import by.parakhnevich.arraysanddecomposition.utilarrays.creating.CreateFileWithRandomNumbers;
import by.parakhnevich.arraysanddecomposition.utilarrays.creating.CreateRandomArray;

import java.io.File;
import java.io.FileNotFoundException;

public class Runner {
    public static void main(String[] args) throws FileNotFoundException {
        CreateFileWithRandomNumbers createFileWithRandomNumbers = new CreateFileWithRandomNumbers();
        createFileWithRandomNumbers.create("file.txt",100);
        SortingOfBigFile sortingOfBigFile = new SortingOfBigFile();
        sortingOfBigFile.sort(new File("file.txt"));

    }
}
