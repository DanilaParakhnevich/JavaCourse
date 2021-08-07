package by.parakhnevich.branchingandloops.utilbranches;


/**
 * Class {@code Object} calculate sum of series
 * its member is 1/n while n > 0
 * TASK09
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class SumOfSimpleSeries {
    private int number;

    public SumOfSimpleSeries(int number){
        this.number = number;
    }

    public double calculate(){
        double sum = 0;
        double numberTemp = number;
        while (numberTemp>0){
            sum += (1.0 / (numberTemp--));
        }
        return sum;
    }

    public int getNumber() {
        return number;
    }
}
