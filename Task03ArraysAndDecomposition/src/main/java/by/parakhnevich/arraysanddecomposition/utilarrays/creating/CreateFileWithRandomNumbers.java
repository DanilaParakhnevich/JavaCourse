package by.parakhnevich.arraysanddecomposition.utilarrays.creating;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class CreateFileWithRandomNumbers {
    public void create(String name, int number){
        File file = new File(name);
        Random random = new Random();
        try {
            FileWriter writer = new FileWriter(file);
            while (number > 0){
                writer.write(random.nextInt(1000) + " ");
                number--;
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
