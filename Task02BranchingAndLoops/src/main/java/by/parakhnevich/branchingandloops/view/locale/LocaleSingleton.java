package by.parakhnevich.branchingandloops.view.locale;

import java.util.ResourceBundle;

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

    public static LocaleSingleton getInstance(String language, String country) {
        if (locale == null) {
            locale = new LocaleSingleton(language,country);
        }
        return locale;
    }
}