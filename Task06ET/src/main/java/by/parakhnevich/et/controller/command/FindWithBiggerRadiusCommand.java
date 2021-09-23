package by.parakhnevich.et.controller.command;

import by.parakhnevich.et.dao.repository.ArraySphereRepository;
import by.parakhnevich.et.dao.repository.specification.findspecification.find.FindWithRadiusBiggerThatValue;
import by.parakhnevich.et.service.output.ListToString;

import java.io.IOException;
import java.util.List;

public class FindWithBiggerRadiusCommand implements Command{
    @Override
    public String execute(List<String> list) throws IOException {
        if (ArraySphereRepository.getInstance().getAll() == null) {
            ArraySphereRepository.getInstance().load("ActualSpheres.txt");
        }
        return "Result :\n" + new ListToString(
                ArraySphereRepository.getInstance().findBySpecification(
                new FindWithRadiusBiggerThatValue(Double.parseDouble(list.remove(0))))).
                get();
    }
}
