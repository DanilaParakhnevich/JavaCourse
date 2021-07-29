package by.parakhnevich.runner;

import by.parakhnevich.entity.*;
import by.parakhnevich.exception.CircleException;
import by.parakhnevich.exception.FileReaderException;
import by.parakhnevich.exception.TriangleException;
import by.parakhnevich.menu.UserInterface;
import by.parakhnevich.reporter.*;
import by.parakhnevich.utillinearalgorithm.calculator.CircleCalculator;
import by.parakhnevich.utillinearalgorithm.calculator.RegularTriangleCalc;
import by.parakhnevich.utillinearalgorithm.finder.FindAngles;
import by.parakhnevich.utillinearalgorithm.finder.FindExpression;
import by.parakhnevich.utillinearalgorithm.finder.FindVariableOfFuncWithThreeVariables;
import by.parakhnevich.validator.CircleValidator;
import by.parakhnevich.validator.TriangleValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.Math.toDegrees;


public class Main {
    public static final Logger logger = LogManager.getLogger(Main.class);
    private static final String TOO_MUCH = "Too much arguments";
    private static final String RESULT_FOR_REGULAR_TRIANGLE = "Area : %.1f\n"
            +"Height : %.1f\n"
            +"Circumscribed radius : %.1f\n"
            +"Inscribed radius : %.1f\n";
    private static final String RESULT_FOR_TRIANGLE = "First angle : %.1f\n"
            + "Second angle : %.1f\n"
            + "Third angle : %.1f\n";
    private static final String RESULT_FOR_TRIANGLE_IN_DEGREES = "In degrees :\n"
            +"First angle : %.1f\n"
            + "Second angle : %.1f\n"
            + "Third angle : %.1f\n";
    private static final String RESULT_FOR_CIRCLE = "Circuit : %.1f\n"
            + "Area : %.1f\n";
    private static final String RESULT_FOR_EXPRESSION = "Result : %.1f";
    private static final String RESULT_FOR_FUNCTION_WITH_THREE_VARIABLES = "Result : %.1f   ";


    /*
    * This runner will work while user don't choose 0
    * File with input data named text.txt
    */
    public static void main(String[] args) throws FileReaderException, IOException {
        logger.log(Level.INFO,"Program is starting");

        UserInterface userInterface = new UserInterface();
        Reporter reporter = new Reporter();

        while (true) {
            int number = userInterface.chooseNumberOfTask();
            if(number == 0) {
                break;
            }
            Scanner scan = userInterface.chooseAMethod();
            try {
                switch (number) {
                    case 1:
                        if (userInterface.getNumberOfMethod() == 2) {
                            System.out.println("Enter side of regular triangle");
                        }
                        else if (scan.hasNextLine())
                            throw new FileReaderException(TOO_MUCH);
                        RegularTriangle regularTriangle = new RegularTriangle(Double.parseDouble(scan.nextLine()));
                        RegularTriangleCalc calcTriangle = new RegularTriangleCalc(regularTriangle);
                        TriangleValidator validator = new TriangleValidator(regularTriangle);

                        if (!validator.isValid())
                            throw new TriangleException("Sides are bad", regularTriangle.getFirst(),
                                    regularTriangle.getSecond(), regularTriangle.getThird());

                        reporter.output(String.format(RESULT_FOR_REGULAR_TRIANGLE,
                                calcTriangle.countArea(), calcTriangle.countHeight(),
                                calcTriangle.countCircumscribedRadius(), calcTriangle.countInscribedRadius()), regularTriangle);
                        break;
                    case 2:
                        if (userInterface.getNumberOfMethod() == 2) {
                            System.out.println("Function is ( ( a - 3 ) * b / 2 ) + c");
                            System.out.println("Enter values of function");
                        }
                        else if (scan.hasNextLine())
                            throw new FileReaderException(TOO_MUCH);

                        FunctionWithThreeVariables function = new FunctionWithThreeVariables(Double.parseDouble(scan.nextLine()), Double.parseDouble(scan.nextLine()), Double.parseDouble(scan.nextLine()));
                        FindVariableOfFuncWithThreeVariables result = new FindVariableOfFuncWithThreeVariables(function);


                        reporter.output(String.format(RESULT_FOR_FUNCTION_WITH_THREE_VARIABLES,
                                result.countResult()), function);
                        break;
                    case 3:
                        if (userInterface.getNumberOfMethod() == 2) {
                            System.out.println("Enter sides of triangle");
                        }
                        else if (scan.hasNextLine())
                            throw new FileReaderException(TOO_MUCH);

                        Triangle triangle = new Triangle(Double.parseDouble(scan.nextLine()), Double.parseDouble(scan.nextLine()), Double.parseDouble(scan.nextLine()));
                        TriangleValidator triangleValidator = new TriangleValidator(triangle);
                        FindAngles angles = new FindAngles(triangle);

                        if (!triangleValidator.isValid())
                            throw new TriangleException("Sides are bad", triangle.getFirst(), triangle.getSecond(), triangle.getThird());


                        reporter.output(String.format(RESULT_FOR_TRIANGLE, angles.countAngleA(),
                                angles.countAngleB(), angles.countAngleC()) +
                                String.format(RESULT_FOR_TRIANGLE_IN_DEGREES,
                                toDegrees(angles.countAngleA()), toDegrees(angles.countAngleB()),
                                toDegrees(angles.countAngleC())), triangle);
                        break;
                    case 4:
                        if (userInterface.getNumberOfMethod() == 2){
                            System.out.println("Enter radius");
                        }
                        else if (scan.hasNextLine())
                            throw new FileReaderException(TOO_MUCH);
                        Circle circle = new Circle(Double.parseDouble(scan.nextLine()));
                        CircleCalculator circleCalculator = new CircleCalculator(circle);
                        CircleValidator circleValidator = new CircleValidator(circle);

                        if(!circleValidator.isValid())
                            throw new CircleException("Radius is bad",circle.getRadius());

                        reporter.output(String.format(RESULT_FOR_CIRCLE,
                                circleCalculator.countCircuit(),circleCalculator.countArea()),
                                circle);
                        break;
                    case 5:
                        if (userInterface.getNumberOfMethod() == 2) {
                            System.out.println("Expression is 2 * x^4 - 3 * x^3 + 4 * x^2 - 5 * x + 6");
                            System.out.println("Enter value of x");
                        }
                        else if (scan.hasNextLine())
                            throw new FileReaderException(TOO_MUCH);
                        Expression expression = new Expression(Double.parseDouble(scan.nextLine()));
                        FindExpression findExpression = new FindExpression(expression);

                        if (userInterface.getNumberOfMethod() == 1 && scan.hasNextLine())
                            throw new FileReaderException(TOO_MUCH);

                        reporter.output(String.format(RESULT_FOR_EXPRESSION,
                                findExpression.countResult()),expression);
                        break;
                    default:
                }
            }
            catch (FileReaderException | TriangleException | CircleException e){
                logger.error(e);
            }
        }
        logger.log(Level.INFO,"Program is finished");
    }
}
