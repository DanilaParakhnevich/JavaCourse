package by.parakhnevich.et.dao;

import by.parakhnevich.et.bean.Sphere;
import by.parakhnevich.et.dao.repository.SphereRegister;
import by.parakhnevich.et.service.calculator.SphereCalculator;
import by.parakhnevich.et.service.validator.SphereIntersectsWithPlanesValidator;
import by.parakhnevich.et.service.validator.SphereValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CloudscapeSphereDAO implements CircleDAO {
    private final FileInfoValidator validator = new FileInfoValidator();
    Logger logger = LogManager.getLogger(CloudscapeSphereDAO.class);
    private static final String MUCH_ARGUMENTS = "Too much arguments";
    private static final String BAD_RADIUS = "Bad radius";


    public List<Sphere> getAll(String name) throws IOException {
        String absolutePath = getPathToFile(name);
        List<String> list = Files.lines(Paths.get(absolutePath)).
                collect(Collectors.toList());
        return generateListOfCircles(list);
    }

    public synchronized void save(List<Sphere> list) {
        try (FileWriter writer = new FileWriter(getPathToFile("ActualSpheres.txt"))){

            for (Sphere circle : list) {
                writer.write(String.valueOf(circle.getRadius())+ ' ');
                writer.write(String.valueOf(circle.getPoint().getX()) + ' ');
                writer.write(String.valueOf(circle.getPoint().getY()) + ' ');
                writer.write(String.valueOf(circle.getPoint().getZ()) + '\n');
            }
        } catch (IOException e) {
            logger.error(e);
        }
    }

    //private methods
    private String getPathToFile(String name) {
        File file = new File("src/main/resources/files/" + name);
        return file.getAbsolutePath();
    }

    private double[] stringToDoubleArray(String line) {
        String[] array = line.split(" ");
        if (!validator.validateSizeOfLineFromFile(array, 4)) {
            logger.error(MUCH_ARGUMENTS);
            return new double[0];
        }
        double[] result = new double[4];
        int i = 0;
        for (String number : array) {
            try {
                result[i++] = Double.parseDouble(number);
            }
            catch (NumberFormatException e) {
                logger.error(e);
                return new double[0];
            }
        }
        return result;
    }

    private Sphere generate(double[] array) {
        Sphere circle = new Sphere(array[0], array[1], array[2], array[3]);
        if (new SphereValidator().validate(circle)){
            return circle;
        }
        else {
            logger.error(BAD_RADIUS);
            return null;
        }
    }

    private List<Sphere> generateListOfCircles(List<String> list) {
        List<Sphere> result = new ArrayList<>();
        for (String line : list) {
            double[] array = stringToDoubleArray(line);
            Sphere sphere = null;
            if (array.length != 0) {
                sphere = generate(array);
            }
            if (sphere != null) {
                sphere.getRegister().setId(SphereRegister.getCurrentId());
                SphereRegister.inc();
                SphereCalculator calculator = new SphereCalculator(sphere);
                sphere.getRegister().setArea(calculator.calculateArea());
                sphere.getRegister().setVolume(calculator.calculateVolume());
                sphere.getRegister().
                        setSphereIntersectsAllThePlanes(
                                new SphereIntersectsWithPlanesValidator().validate(sphere));
                result.add(sphere);
            }
        }
        return result;
    }
}
