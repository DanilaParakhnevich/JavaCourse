package by.parakhnevich.multithreadingmatrix.controller.runner;

import by.parakhnevich.multithreadingmatrix.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Runner {
    private static final Logger logger = LogManager.getLogger(Runner.class);
    private static final View view = new View();

    public static void main(String[] args) {
        logger.info("Start application");
        view.createLocale();
        view.execute();
        logger.info("Finish application");
    }
}
