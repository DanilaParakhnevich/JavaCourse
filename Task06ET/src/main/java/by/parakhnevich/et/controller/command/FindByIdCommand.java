package by.parakhnevich.et.controller.command;

import by.parakhnevich.et.dao.repository.ArraySphereRepository;
import by.parakhnevich.et.dao.repository.specification.findspecification.find.FindById;
import by.parakhnevich.et.service.FindSphereBySpecification;
import by.parakhnevich.et.service.output.ListToString;

import java.io.IOException;
import java.util.List;

public class FindByIdCommand implements Command{

    @Override
    public String execute(List<String> list) throws IOException {
        return "Result :\n" + new ListToString(
                new FindSphereBySpecification().execute(
                ArraySphereRepository.getInstance().getAll(),
                        new FindById(Integer.parseInt(list.get(0)))))
                .get();

    }
}
