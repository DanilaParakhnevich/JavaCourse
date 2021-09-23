package by.parakhnevich.et.service.output;

import by.parakhnevich.et.bean.Sphere;

import java.util.List;

public class ListToString {
    List<Sphere> list;
    public ListToString(List<Sphere> list) {
        this.list = list;
    }

    public String get() {
        if (list.isEmpty()) {
            return "List is clear\n";
        }
        StringBuilder result = new StringBuilder();
        int index = 1;
        result.append("Actual list of spheres").append('\n');
        for (Sphere obj : list) {
            result.append(index++).append(". ").append(obj.toString()).append('\n');
        }
        return result.toString();
    }

}
