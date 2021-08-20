package by.parakhnevich.arraysanddecomposition.utilarrays;

/**
 * This comparator help to compare 2 numbers
 * of the same type
 * @autor Danila Parakhnevich
 * @version 1.0
 */
public class CompareTwoNumbers {
    private CompareTwoNumbers() {
    }

    public static boolean compare(Number first, Number second) {
        if (Double.class.isAssignableFrom(first.getClass())) {
            return first.doubleValue() > second.doubleValue();
        }
        else if (Integer.class.isAssignableFrom(first.getClass())) {
            return first.intValue() > second.intValue();
        }
        else if (Short.class.isAssignableFrom(first.getClass())) {
            return first.shortValue() > second.shortValue();
        }
        else if (Long.class.isAssignableFrom(first.getClass())) {
            return first.longValue() > second.longValue();
        }
        else if (Byte.class.isAssignableFrom(first.getClass())) {
            return first.byteValue() > second.byteValue();
        }
        else if (Float.class.isAssignableFrom(first.getClass())) {
            return first.floatValue() > second.floatValue();
        }
        return false;
    }
}
