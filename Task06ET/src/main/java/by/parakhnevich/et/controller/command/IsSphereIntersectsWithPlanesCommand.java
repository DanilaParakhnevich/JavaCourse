package by.parakhnevich.et.controller.command;

import by.parakhnevich.et.dao.repository.ArraySphereRepository;
import by.parakhnevich.et.service.validator.SphereIntersectsWithPlanesValidator;

import java.io.IOException;
import java.util.List;

public class IsSphereIntersectsWithPlanesCommand implements Command{
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
        return "Result : " + new SphereIntersectsWithPlanesValidator().validate(
                ArraySphereRepository.getInstance().getAll().get(index));
    }
}
