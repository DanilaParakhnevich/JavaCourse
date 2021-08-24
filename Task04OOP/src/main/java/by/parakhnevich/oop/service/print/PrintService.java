package by.parakhnevich.oop.service.print;

import java.util.Map;

public class PrintService {
    public String get(Map<Integer, String> map) {
        int index = 1;
        StringBuilder stringBuilder = new StringBuilder();
        while (map.get(index) != null){
            stringBuilder.append(index).
                    append(".").append(map.get(index)).append('\n');
            index++;
        }
        return stringBuilder.toString();
    }
}
