package by.parakhnevich.multithreadingmatrix.controller.command;

import by.parakhnevich.multithreadingmatrix.bean.Matrix;
import by.parakhnevich.multithreadingmatrix.dao.ListForThreadsDAO;
import by.parakhnevich.multithreadingmatrix.dao.MatrixMultithreadingDAO;
import by.parakhnevich.multithreadingmatrix.dao.exception.DAOException;
import by.parakhnevich.multithreadingmatrix.service.PutNumbersInMainDiagonal;
import by.parakhnevich.multithreadingmatrix.service.creators.SimpleThreadsCreator;
import by.parakhnevich.multithreadingmatrix.view.locale.LocaleSingleton;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PuttingSimpleThreadsCommand implements Command{
    @Override
    public String execute(List<String> list) throws InterruptedException, IOException, DAOException {
        Matrix matrix = new MatrixMultithreadingDAO().getMatrix();
        StringBuilder result = new StringBuilder().append(LocaleSingleton.
                getInstance().getResourceBundle().getString("FIFTH")).append(
                new GetMainIntroductionToVariant().get(matrix)
        );
        List<Integer> listForThreads = new ListForThreadsDAO().getList();
        new PutNumbersInMainDiagonal().execute(new SimpleThreadsCreator().create(listForThreads, matrix));
        TimeUnit.MILLISECONDS.sleep(matrix.getRows() * 50L);
        PutNumbersInMainDiagonal.reset();
        return result.append(matrix.toPureString()).toString();
    }
}
