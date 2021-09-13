package by.parakhnevich.multithreadingmatrix.dao;

import by.parakhnevich.multithreadingmatrix.dao.exception.DAOException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListForThreadsDAO {
   public List<Integer> getList() throws DAOException, IOException {
       String absolutePath = new PathOfFileFor5Task().get("thread.txt");
       List<String> list = Files.lines(Paths.get(absolutePath)).
               collect(Collectors.toList());
       List<String> limitList = List.of(list.remove(0).split(" "));
       List<String> listOfNumbersForThreads = List.of(list.remove(0).split(" "));
       int count = listOfNumbersForThreads.size();
       if (new LimitsValidator().validate(count, Integer.parseInt(limitList.get(0)),
               Integer.parseInt(limitList.get(1)))){
           throw new DAOException();
       }
       List<Integer> result = new ArrayList<>();
       listOfNumbersForThreads.forEach(x -> result.add(Integer.parseInt(x)));
       return result;
   }
}
