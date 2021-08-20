package by.parakhnevich.arraysanddecomposition.view.locale;

import java.util.ResourceBundle;

/**
 * Class {@code Object} is incarnation Singleton pattern
 * It made for localization of program
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class LocaleSingleton {
    private static LocaleSingleton locale;

    private static ResourceBundle resourceBundle;

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    private LocaleSingleton(String language, String country) {
        resourceBundle = ResourceBundle.getBundle("language.locale",
                new java.util.Locale(language,country));
    }

    public static void getInstance(String language, String country) {
        if (locale == null) {
            locale = new LocaleSingleton(language, country);
        }
    }
}