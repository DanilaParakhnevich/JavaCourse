package by.parakhnevich.arraysanddecomposition.utilarrays;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SortingOfBigFile{
    public void sort(File file) throws FileNotFoundException {
        int[] array = new int[findMaxValue(file) + 1];
        Scanner scan = new Scanner(file);
        File result = new File("result.txt");
        while (scan.hasNext()) {
           array[scan.nextInt()]++;
        }
        scan.close();
        write(array);
    }

    private int findMaxValue(File file) throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        int value = scan.nextInt();
        while (scan.hasNext()) {
            int nextValue = scan.nextInt();
            value = Math.max(nextValue, value);
        }
        return value;
    }

    private void write(int[] array){
        File file = new File("result.txt");
        try {
            FileWriter writer = new FileWriter(file);
            for (int index = 0 ; index < array.length ; index++){
                for (int index1 = 0 ; index1 < array[index] ; index1++){
                    writer.write(index + " ");
                    System.out.println(index);
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
