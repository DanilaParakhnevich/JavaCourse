package by.parakhnevich.oop.controller.runner;

import by.parakhnevich.oop.view.View;
import by.parakhnevich.oop.view.ViewReader;

/**
 * Main class
 * @autor Danila Parakhnevich
 * @version 1.1
 */
public class Runner {
    public static void main(java.lang.String[] args) {
            View view = new View();
            new ViewReader().createLocale();
            view.execute();
    }
}
