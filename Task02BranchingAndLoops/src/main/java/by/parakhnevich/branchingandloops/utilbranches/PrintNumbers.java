package by.parakhnevich.branchingandloops.utilbranches;


/**
 * Class {@code Object} return all the even numbers from first
 * value to the second value
 * TASK07
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class PrintNumbers {
    private final int first;
    private final int second;

    public PrintNumbers(int first, int second){
        this.first = first;
        this.second = second;
    }

    PrintNumbers(){
        this.first = 2;
        this.second = 100;
    }

    //get subsequence of numbers from first to second
    public String subs(){
        StringBuilder result = new StringBuilder();
        int firstTemp = first;
        while (firstTemp <= this.second) {
            if (firstTemp % 2 == 0) {
                result.append(String.valueOf(firstTemp) + " ");
            }
            firstTemp++;
        }
        return result.toString();
    }
}
