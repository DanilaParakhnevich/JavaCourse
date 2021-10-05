    package by.parakhnevich.informationhandling.service;

    import by.parakhnevich.informationhandling.bean.interpreter.Number;
    import by.parakhnevich.informationhandling.bean.interpreter.PartOfExpression;
    import by.parakhnevich.informationhandling.bean.interpreter.operations.*;
    import by.parakhnevich.informationhandling.service.validator.LetterService;

    import java.util.*;

    public class Evaluator implements PartOfExpression {
        PartOfExpression evaluate;
        List<String> list = new ArrayList<>();
        LetterService service = new LetterService();

        public Evaluator(String expression) {
            Queue<Character> queue = new ArrayDeque<>();
            for (int i = 0; i < expression.toCharArray().length; ++i) {
                queue.add(expression.toCharArray()[i]);
            }
            String part = getSomething(queue);
            Deque<String> stack = new ArrayDeque<>();
            String prefix = "";
            while (!part.equals("")) {
                if (service.isNumber(part)) {
                    list.add(part);
                    list.add(prefix);
                    prefix = "";
                } else if (part.equals("(")) {
                    stack.push(part);
                } else if (part.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        list.add(stack.poll());
                    }
                    stack.pop();
                } else if (part.equals("~")) {
                    prefix = part;
                } else if (!stack.isEmpty() &&
                        getPriority(part) < getPriority(stack.peek())) {
                    list.add(stack.poll());
                    stack.push(part);
                } else {
                    stack.push(part);
                }
                part = getSomething(queue);
            }
            while (!stack.isEmpty()) {
                list.add(stack.poll());
            }
        }


        public String getSomething(Queue<Character> queue) {
            StringBuilder builder = new StringBuilder();
            if (queue.isEmpty()) {
                return "";
            }
            if (queue.peek().equals(')')) {
                queue.poll();
                return ")";
            }
            char begin = queue.poll();
            builder.append(begin);
            if (service.isNumber(String.valueOf(begin))) {
                while (!queue.isEmpty() &&
                        service.isNumber(String.valueOf(queue.peek()))) {
                    builder.append(queue.poll());
                }
            }
            else {
                while (!queue.isEmpty() && queue.peek() == begin) {
                    builder.append(queue.poll());
                }
            }
            return builder.toString();
        }

        public int getPriority(String sign) {
            switch (sign) {
                case ">>":
                case "<<":
                case ">>>":
                    return 6;
                case "|":
                case "&":
                case "^":
                    return 8;
                case "(":
                case ")":
                    return 1;
                case "~":
                    return 9;
                default:
                    return -1;
            }
        }

        @Override
        public double interpret(PartOfExpression context) {
            Stack<Double> result = new Stack<>();
            while (!list.isEmpty()) {
                if (list.get(0).equals("")) {
                    break;
                }
                if (service.isNumber(list.get(0))) {
                    result.push(Double.parseDouble(list.remove(0)));
                }
                else if (list.get(0).equals("~")){
                    result.push(
                            new UnaryNot(
                                    new Number(result.pop())).interpret(null));
                    list.remove(0);
                }
                else {
                    result.push(binaryOperation(list.remove(0),
                            new Number(Double.parseDouble(list.remove(0))),
                            new Number(Double.parseDouble(list.remove(0)))));
                }
            }
            return result.pop();
        }
        private double binaryOperation(String str, Number number1, Number number2) {
            switch (str) {
                case ">>":
                    return new RightShift(number1, number2).interpret(null);
                case "<<":
                    return new LeftShift(number1, number2).interpret(null);
                case "^":
                    return new BitwiseExcludingOr(number1, number2).interpret(null);
                case "|":
                    return new BitwiseOr(number1, number2).interpret(null);
                case "&":
                    return new BitwiseAnd(number1, number2).interpret(null);
                case ">>>":
                    return new RightShiftFillingWithZeros(number1, number2).interpret(null);
                default:
                    return 0;
            }
        }
    }

