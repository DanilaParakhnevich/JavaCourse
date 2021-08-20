package by.parakhnevich.arraysanddecomposition.runner;


import by.parakhnevich.arraysanddecomposition.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class {@code Object} is Main Class
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class Runner {
    private static final Logger logger = LogManager.getLogger(Runner.class.getName());

    public static void main(String[] args) throws Exception {
        logger.info("Application has started");
        View view = new View();
        view.createLocale();
        view.view();
        logger.info("Application is finishing");
    }
}
