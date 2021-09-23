package by.parakhnevich.et.controller.command;

import by.parakhnevich.et.dao.repository.ArraySphereRepository;

import java.io.IOException;
import java.util.List;

public class SaveChangesCommand implements Command{
    @Override
    public String execute(List<String> list) throws IOException {
        ArraySphereRepository.getInstance().save();
        return "Success\n";
    }
}
