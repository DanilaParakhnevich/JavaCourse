package by.parakhnevich.arraysanddecomposition.reader;

import by.parakhnevich.arraysanddecomposition.view.locale.LocaleSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ReadArray implements Reader{
    Random rand = new Random();

    @Override
    public List<String> read(Scanner scan, boolean[] flags) throws Exception {
        List<String> list = new ArrayList<>();
        if (!flags[1] && !flags[0]) {
            System.out.println(LocaleSingleton.getResourceBundle().getString("ARRAY_INITIALIZATION"));
            System.out.println(LocaleSingleton.getResourceBundle().getString("SIZE_ARRAY"));
            list.add(scan.next());
            for (int index = 0; index < Integer.parseInt(list.get(0)); index++){
                list.add(scan.next());
            }
        }
        else if (!flags[1]) {
            list.add(scan.next());
            for (int index = 0; index < Integer.parseInt(list.get(0)); index++) {
                list.add(String.valueOf(scan.nextInt()));
            }
        }
        else {
            System.out.println(LocaleSingleton.getResourceBundle().getString("SIZE_ARRAY"));
            list.add(scan.next());
            int length = flags[2]?Integer.parseInt(list.get(0)) + 1 :
                    Integer.parseInt(list.get(0));
            for (int index = 0; index < length; index++) {
                list.add(String.valueOf(rand.nextInt(100)));
            }
        }

        if (flags[0] && scan.hasNext()){
            throw new ReadException();
        }

        return list;
    }
}
