package by.parakhnevich.et.controller.command;

import by.parakhnevich.et.dao.repository.ArraySphereRepository;
import by.parakhnevich.et.service.output.ListToString;
import by.parakhnevich.et.dao.repository.specification.sortspecification.sort.SortViaId;

import java.io.IOException;
import java.util.List;

public class SortViaIdCommand implements Command{
    @Override
    public String execute(List<String> list) throws IOException {
        if (ArraySphereRepository.getInstance().getAll() == null) {
            ArraySphereRepository.getInstance().load("ActualSpheres.txt");
        }
        return new ListToString(ArraySphereRepository.getInstance().
                sortBySpecification( new SortViaId())).
                get();
    }
}
