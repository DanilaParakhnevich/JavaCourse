package by.parakhnevich.arraysanddecomposition.utilarrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SortingOfBigFile{
    private final Logger logger = LogManager.getLogger(SortingOfBigFile.class.getName());
    public void sort(File file) throws FileNotFoundException {
        int[] arrayPositive = new int[findMaxValue(file) + 1];
        int min = findMinValue(file);
        int[] arrayNegative = new int[findMinValue(file) + 1];
        if (min < 0){
            arrayNegative = new int[-findMinValue(file) + 1];
        }
        else {
            arrayNegative = null;
        }
        Scanner scan = new Scanner(file);
        while (scan.hasNext()) {
           int number = scan.nextInt();
           if (number < 0){
               assert arrayNegative != null;
               arrayNegative[-number]++;
           }
           else {
               arrayPositive[number]++;
           }
       }
        scan.close();
        if(arrayNegative != null) {
            write(arrayNegative);
        }
        write(arrayPositive);
    }

    private int findMaxValue(File file) throws FileNotFoundException {
        int value;
        try (Scanner scan = new Scanner(file)) {
            value = scan.nextInt();
            while (scan.hasNext()) {
                int nextValue = scan.nextInt();
                value = Math.max(nextValue, value);
            }
        }
        return value;
    }

    private int findMinValue(File file) throws FileNotFoundException {
        int value;
        try (Scanner scan = new Scanner(file)) {
            value = scan.nextInt();
            while (scan.hasNext()) {
                int nextValue = scan.nextInt();
                value = Math.min(nextValue, value);
            }
        }
        return value;
    }

    private void write(int[] array){
        File file = new File("result.txt");
        try {
            try (FileWriter writer = new FileWriter(file)) {
                for (int index = 0; index < array.length; index++) {
                    for (int index1 = 0; index1 < array[index]; index1++) {
                        writer.write(index + " ");
                    }
                }
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
