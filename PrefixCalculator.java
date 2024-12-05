import java.util.ArrayList;
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

        ArrayList<String> reversedExpression = new ArrayList<>();
        for (int i = expression.size() - 1; i >= 0; i--) {
            String token = expression.get(i);
            if (token.equals("(")) {
                reversedExpression.add(")");
            } else if (token.equals(")")) {
                reversedExpression.add("(");
            } else {
                reversedExpression.add(token);
            }
        }

        for (String key : reversedExpression) {
            if (key.equals("+") || key.equals("-") || key.equals("/") || key.equals("*") || key.equals("^")) {
                while (!operatorStack.isEmpty() && precedence(key) < precedence(operatorStack.peek())) {
                    prefixStack.push(operatorStack.pop());
                }
                operatorStack.push(key);
            } else if (key.equals("(")) {
                operatorStack.push(key);
            } else if (key.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    prefixStack.push(operatorStack.pop());
                }
                operatorStack.pop();
            } else {
                prefixStack.push(key);
            }
        }

        while (!operatorStack.isEmpty()) {
            prefixStack.push(operatorStack.pop());
        }

        ArrayList<String> prefix = new ArrayList<>();
        while (!prefixStack.isEmpty()) {
            prefix.add(0, prefixStack.pop());
        }

        return prefix;
    }

    @Override
    public double calculate() {
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

