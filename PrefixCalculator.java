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

        for (String key : expression) { // goes through each piece in expression
            if (key.equals("+") || key.equals("-") || key.equals("/") || key.equals("*") || key.equals("(") || key.equals(")")) {

                if (key.equals("(")) {
                    operatorStack.push(key); // push to stack for subexpression start
                }

                else if (key.equals(")")) {
                    // Pop operators from the operator stack and push them onto the prefix stack
                    // until an opening parenthesis is encountered
                    while (!operatorStack.isEmpty() && !Objects.equals(operatorStack.peek(), "(")) {
                        String operator = operatorStack.pop(); // move to prefix stack when finished
                        prefixStack.push(operator);
                    }
                    operatorStack.pop(); // get rid of opening parenthesis
                }

                else {
                    // Pop higher or equal-precedence operators from the operator stack
                    // move high precedence operators to precedence stack
                    while (!operatorStack.isEmpty() && hasPrecidence(key, operatorStack.peek())) {
                        String operator = operatorStack.pop();
                        prefixStack.push(operator);
                    }
                    operatorStack.push(key); // move current operator to the stack
                }
            }

            // numbers
            else {
                prefixStack.push(key); // straight to stack
            }
        }

        // move anything else to the prefix stack
        while (!operatorStack.isEmpty()) {
            String operator = operatorStack.pop();
            prefixStack.push(operator);
        }

        // convert to prefix expression
        ArrayList<String> prefix = new ArrayList<>();
        while (!prefixStack.isEmpty()) {
            String element = prefixStack.pop();
            prefix.add(0, element); // add them to the beginning of the array so they are in the right order
        }
        return prefix;
    }

    @Override
    public double calculate() {
        System.out.println("Prefix Expression: " + prefixExpression);

        Stack<Double> expressionStack = new Stack<>();

        // goes through the prefix expression right to left
        for (int i = prefixExpression.size() - 1; i >= 0; i--) {
            String key = prefixExpression.get(i);
            try {
                Double number = Double.valueOf(key); // change to double
                expressionStack.push(number);
            } catch (NumberFormatException e) { // if it is not a number than it has to be an operator
                double val1 = expressionStack.pop();
                double val2 = expressionStack.pop();

                double result = operate(key, val1, val2);
                expressionStack.push(result);
            }
        }

        return expressionStack.pop();
    }
}
