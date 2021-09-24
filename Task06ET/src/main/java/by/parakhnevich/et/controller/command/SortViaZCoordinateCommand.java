package by.parakhnevich.et.controller.command;

import by.parakhnevich.et.dao.repository.ArraySphereRepository;
import by.parakhnevich.et.dao.repository.specification.sortspecification.sort.SortViaZCoordinate;
import by.parakhnevich.et.service.SortSphereBySpecification;
import by.parakhnevich.et.service.output.ListToString;

import java.io.IOException;
import java.util.List;

public class SortViaZCoordinateCommand implements Command{
    @Override
    public String execute(List<String> list) throws IOException {
        return new ListToString(new SortSphereBySpecification().
                execute(ArraySphereRepository.getInstance().getAll(),
                        new SortViaZCoordinate())).get();
    }
}
