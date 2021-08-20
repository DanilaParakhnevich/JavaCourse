package by.parakhnevich.arraysanddecomposition.reader;

import by.parakhnevich.arraysanddecomposition.view.locale.LocaleSingleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ReadMatrix implements Reader {
    private final Random rand = new Random();
    private boolean flag = true;

    @Override
    public List<String> read(Scanner scan, boolean[] flags) throws Exception {
        List<String> list = new ArrayList<>();
        if (!flags[1] && !flags[0]) {
            System.out.println(LocaleSingleton.getResourceBundle().getString("MATRIX_INITIALIZATION"));
            System.out.println(LocaleSingleton.getResourceBundle().getString("SIZE_N"));
            list.add(scan.next());
            System.out.println(LocaleSingleton.getResourceBundle().getString("SIZE_M"));
            list.add(scan.next());
            for (int index = 0; index < Integer.parseInt(list.get(0))*Integer.parseInt(list.get(1))
                    ; index++) {
                list.add(scan.next());
            }
            if (flags[2]){
                System.out.println(LocaleSingleton.getResourceBundle().getString("VALUE"));
                list.add(scan.next());
            }
        }
        else if (!flags[1]) {
            list.add(String.valueOf(scan.nextInt()));
            list.add(String.valueOf(scan.nextInt()));
            int length = flags[2] ? 1 : 0;
            for (int index = 0; index < Integer.parseInt(list.get(0)) * Integer.parseInt(list.get(1))
                    + length; index++) {
                list.add(String.valueOf(scan.nextInt()));
            }
            if (scan.hasNext() && flag){
                throw new ReadException();
            }
        }
        else {
            System.out.println(LocaleSingleton.getResourceBundle().getString("MATRIX_INITIALIZATION"));
            System.out.println(LocaleSingleton.getResourceBundle().getString("SIZE_N"));
            list.add(scan.next());
            System.out.println(LocaleSingleton.getResourceBundle().getString("SIZE_M"));
            list.add(scan.next());
            for (int index = 0; index < Integer.parseInt(list.get(0))*Integer.parseInt(list.get(1))
                    ; index++) {
                list.add(String.valueOf(rand.nextInt(100)));
            }
            if (flags[2]) {
                list.add(String.valueOf(rand.nextInt(100)));
            }
        }
        return list;
    }

    public void flagOff(){
        flag = false;
    }
}
