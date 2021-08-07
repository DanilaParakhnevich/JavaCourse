package by.parakhnevich.branchingandloops.utilbranches;

/**
 * Class {@code Object} find magic number removing the higher digits
 * and multiplying by 7
 * TASK10
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class MagicNumber {
    private String simpleNumber;

    public MagicNumber(int simpleNumber) {
        this.simpleNumber = String.valueOf(simpleNumber);
    }

    public int find(){
        char element = 0;
        for (char number : this.simpleNumber.toCharArray()) {
            if (number != '-' && element < number) {
                element = number;
            }
        }
        return Integer.parseInt(simpleNumber.replace(String.valueOf(element) , "")) * 7;
    }
}
