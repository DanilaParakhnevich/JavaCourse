package by.parakhnevich.branchingandloops.utilbranches;

/**
 * Class {@code Object} find common part of two natural numbers
 * TASK08
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class CommonPartOfTwoNumbers {
    private String first;
    private String second;

    public CommonPartOfTwoNumbers(int first,int second) {
        this.first = String.valueOf(first);
        this.second = String.valueOf(second);
    }

    public String find() {
        StringBuilder result = new StringBuilder();
        for (char element : first.toCharArray()) {
            if (second.indexOf(element) != -1 && result.toString().indexOf(element) == -1){
                result.append(element);
            }
        }
        return result.toString().trim();
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }
}
