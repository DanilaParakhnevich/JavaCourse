package by.parakhnevich.informationhandling.service.validator;

public class LetterService {
    public boolean isLetter(String el) {
        char[] ar = el.toCharArray();
        char element = ar[0];
        return element >= 'A' && element <= 'Z' ||
                element >= 'a' && element <= 'z' ||
                element >= 'а' && element <= 'я' ||
                element >= 'А' && element <= 'Я';
    }

    public boolean isExpression(String el) {
        for (int i = 0; i < el.toCharArray().length; ++i) {
            if (el.toCharArray()[i] >= '0' && el.toCharArray()[i] <= '9') {
                return true;
            }
        }
        return false;
    }

    public boolean isNumber(String el) {
        for (int i = 0; i < el.toCharArray().length; ++i) {
            if (el.toCharArray()[i] < '0' ||
                    el.toCharArray()[i] > '9') {
                return false;
            }
        }
        return true;
    }

    public boolean isLetter(char element) {
        return element >= 'A' && element <= 'Z' ||
                element >= 'a' && element <= 'z' ||
                element >= 'а' && element <= 'я' ||
                element >= 'А' && element <= 'Я';
    }
}
