package by.parakhnevich.et.controller.command;

import by.parakhnevich.et.dao.repository.ArraySphereRepository;
import by.parakhnevich.et.service.output.ShowCharacteristicsOfSphere;

import java.io.IOException;
import java.util.List;

public class ShowCharacteristicsOfSphereCommand implements Command{

    @Override
    public String execute(List<String> list) throws IOException {
        if (ArraySphereRepository.getInstance().getAll() == null) {
            ArraySphereRepository.getInstance().load("ActualSpheres.txt");
        }
        int index = Integer.parseInt(list.remove(0));
        if (index < 0 ||
                index >= ArraySphereRepository.getInstance().getAll().size()){
            return "Bad value";
        }
        return new ShowCharacteristicsOfSphere().
                show(ArraySphereRepository.getInstance().getAll().get(index)) + '\n';
    }
}
