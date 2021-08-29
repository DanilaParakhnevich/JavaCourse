package by.parakhnevich.oop.controller.runner;

import by.parakhnevich.oop.controller.Controller;
import by.parakhnevich.oop.view.View;
import by.parakhnevich.oop.view.ViewReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

/**
 * Main class
 * @autor Danila Parakhnevich
 * @version 1.1
 */
public class Runner {
    private static final Logger logger = (Logger)
            LogManager.getLogger(Controller.class.getName());

    public static void main(java.lang.String[] args) {
        logger.info("Program start");
        View view = new View();
        new ViewReader().createLocale();
        view.execute();
        logger.info("Program stop");
    }
}
