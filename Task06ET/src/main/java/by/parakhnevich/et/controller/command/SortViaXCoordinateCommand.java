package by.parakhnevich.et.controller.command;

import by.parakhnevich.et.dao.repository.ArraySphereRepository;
import by.parakhnevich.et.service.SortSphereBySpecification;
import by.parakhnevich.et.service.output.ListToString;
import by.parakhnevich.et.dao.repository.specification.sortspecification.sort.SortViaXCoordinate;

import java.io.IOException;
import java.util.List;

public class SortViaXCoordinateCommand implements Command{
    @Override
    public String execute(List<String> list) throws IOException {
        return new ListToString(new SortSphereBySpecification().
                execute(ArraySphereRepository.getInstance().getAll(),
                        new SortViaXCoordinate())).get();
    }
}
