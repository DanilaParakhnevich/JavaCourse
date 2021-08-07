package by.parakhnevich.branchingandloops.runner;

import by.parakhnevich.branchingandloops.view.View;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Main Class {@code Object} that call View - class
 * @autor Danila Parakhnevich
 * @version 1.0
 * @see by.parakhnevich.branchingandloops.view.View
 */
public class Runner {
    private static final Logger logger = LogManager.getLogger(Runner.class.getName());
    public static void main(String[] args) {
        logger.info("Application has started");
        View view = new View();
        view.createLocale();
        view.view();
        logger.info("Application is finishing");
    }
}
