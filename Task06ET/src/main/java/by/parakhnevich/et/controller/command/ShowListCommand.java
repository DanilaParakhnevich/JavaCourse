package by.parakhnevich.et.controller.command;

import by.parakhnevich.et.dao.repository.ArraySphereRepository;
import by.parakhnevich.et.service.output.ListToString;

import java.io.IOException;
import java.util.List;

public class ShowListCommand implements Command{
    @Override
    public String execute(List<String> list) throws IOException {
        return new ListToString(ArraySphereRepository.getInstance().getAll()).get();
    }
}
