package by.parakhnevich.multithreadingmatrix.view.locale;

import java.util.ResourceBundle;

public class LocaleSingleton {
    private static final LocaleSingleton instance = new LocaleSingleton();
    private ResourceBundle resourceBundle;

    private LocaleSingleton(){}

    public void createLocale(String language, String country){
        resourceBundle = ResourceBundle.getBundle("language.locale",
                new java.util.Locale(language, country));
    }

    public ResourceBundle getResourceBundle() {
        return this.resourceBundle;
    }

    public static LocaleSingleton getInstance(){
        return instance;
    }
}
