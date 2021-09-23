package by.parakhnevich.et.service.output;

import by.parakhnevich.et.bean.Sphere;
import by.parakhnevich.et.service.calculator.SphereCalculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class ShowCharacteristicsOfSphere {
    Logger logger = LogManager.getLogger(ShowCharacteristicsOfSphere.class);

    public String show(Sphere sphere) {
        SphereCalculator calculator = new SphereCalculator(sphere);
        return sphere.toString() + " volume = " +
                calculator.calculateVolume() +
                " area = " + calculator.calculateArea();
    }
}
