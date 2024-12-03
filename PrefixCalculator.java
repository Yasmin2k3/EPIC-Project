import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class PrefixCalculator extends Calculator {
    ArrayList<String> expression;
    ArrayList<String> prefixExpression;

    public PrefixCalculator(ArrayList<String> expression) {
        this.expression = expression;
        this.prefixExpression = infixToPrefix();
    }

    private ArrayList<String> infixToPrefix() {
        Stack<String> operatorStack = new Stack<>();
        Stack<String> prefixStack = new Stack<>();

        for (String key : expression) {
            if (key.equals("+") || key.equals("-") || key.equals("/") || key.equals("*") || key.equals("(") || key.equals(")")) {
                if (key.equals("(")) {
                    operatorStack.push(key);
                } else if (key.equals(")")) {
                    while (!operatorStack.isEmpty() && !Objects.equals(operatorStack.peek(), "(")) {
                        String operator = operatorStack.pop();
                        prefixStack.push(operator);
                    }
                    operatorStack.pop();
                } else {
                    while (!operatorStack.isEmpty() && hasPrecidence(key, operatorStack.peek())) {
                        String operator = operatorStack.pop();
                        prefixStack.push(operator);
                    }
                    operatorStack.push(key);
                }
            } else {
                prefixStack.push(key);
            }
        }

        while (!operatorStack.isEmpty()) {
            String operator = operatorStack.pop();
            prefixStack.push(operator);
        }

        ArrayList<String> prefix = new ArrayList<>();
        while (!prefixStack.isEmpty()) {
            String element = prefixStack.pop();
            prefix.add(0, element);
        }

        return prefix;
    }

    @Override
    public double calculate() {
        System.out.println("Prefix Expression: " + prefixExpression);
        Stack<Double> expressionStack = new Stack<>();

        for (int i = prefixExpression.size() - 1; i >= 0; i--) {
            String key = prefixExpression.get(i);
            try {
                Double number = Double.valueOf(key);
                expressionStack.push(number);
            } catch (NumberFormatException e) {
                double val1 = expressionStack.pop();
                double val2 = expressionStack.pop();
                double result = operate(key, val1, val2);
                expressionStack.push(result);
            }
        }

        return expressionStack.pop();
    }
}
