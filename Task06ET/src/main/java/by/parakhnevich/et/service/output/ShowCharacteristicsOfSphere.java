package by.parakhnevich.et.service.output;

import by.parakhnevich.et.bean.Sphere;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




public class ShowCharacteristicsOfSphere {
    Logger logger = LogManager.getLogger(ShowCharacteristicsOfSphere.class);

    public String show(Sphere sphere) {
        return sphere.toString() + '\n' +
                sphere.getRegister().toString();
    }
}
