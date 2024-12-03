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
        Stack<String> operatorStack = new Stack<>(); // holds operations and parentheses

        // Temporarily holds the postfix-like structure before final reversal into prefix format
        Stack<String> expressionStack = new Stack<>(); // holds postfix structure

        // Step 1: Reverse the infix expression to facilitate prefix conversion
        // In prefix conversion, we process the expression in reverse (from right to left)
        ArrayList<String> reversedExpression = new ArrayList<>(expression); // Create a copy of the original expression
        java.util.Collections.reverse(reversedExpression);                  // Reverse the order of the elements

        // Step 2: Iterate through each token in the reversed expression
        for (String key : reversedExpression) {
            // Check if the current token is an operator or a parenthesis
            if (key.equals("+") || key.equals("-") || key.equals("/") || key.equals("*") || key.equals("(") || key.equals(")")) {
                // If the token is a closing parenthesis `)`, push it to the operator stack
                if (key.equals(")")) {
                    operatorStack.push(key); // Parentheses are handled specially; closing parenthesis is pushed to the stack
                } else if (key.equals("(")) {
                    // If the token is an opening parenthesis `(`:
                    // Pop operators from the operator stack and push them to the expression stack until a closing parenthesis `)` is encountered
                    while (!operatorStack.isEmpty() && !Objects.equals(operatorStack.peek(), ")")) {
                        String operator = operatorStack.pop(); // Remove operator from the top of the stack
                        expressionStack.push(operator);       // Add the operator to the expression stack
                    }
                    operatorStack.pop(); // Remove the closing parenthesis `)` from the operator stack
                } else {
                    // For other operators (`+`, `-`, `*`, `/`):
                    // Maintain precedence: Pop operators from the operator stack that have higher or equal precedence
                    while (!operatorStack.isEmpty() && hasPrecidence(operatorStack.peek(), key)) {
                        String operator = operatorStack.pop(); // Pop the operator from the operator stack
                        expressionStack.push(operator);        // Push the operator to the expression stack
                    }
                    operatorStack.push(key); // Push the current operator to the operator stack
                }
            } else {
                // If the token is not an operator or parenthesis (i.e., it is a number or variable):
                // Directly push it to the expression stack
                expressionStack.push(key);
            }
        }

        // Step 3: After processing all tokens, pop remaining operators from the operator stack
        while (!operatorStack.isEmpty()) {
            String operator = operatorStack.pop(); // Pop the operator from the stack
            expressionStack.push(operator);        // Push it to the expression stack
        }

        // Step 4: Reverse the expression stack to produce the correct prefix notation
        // The expression stack currently holds a reversed postfix-like structure
        ArrayList<String> prefix = new ArrayList<>();          // Initialize the prefix list
        while (!expressionStack.isEmpty()) {
            String element = expressionStack.pop();            // Remove elements from the stack one by one
            prefix.add(element);                               // Add each element to the prefix list
        }

        return prefix; // Return the resulting prefix expression as a list of tokens
    }

    // Method: calculate
    // Evaluates the prefix expression and returns the result as a double value
    @Override
    public double calculate() {
        // Debugging output: Print the prefix expression before evaluation
        System.out.println("Prefix Expression: " + prefixExpression);

        // Stack: expressionStack
        // Used to hold intermediate results during evaluation
        Stack<Double> expressionStack = new Stack<>();

        // Iterate through each token in the prefix expression
        for (String key : prefixExpression) {
            try {
                // Attempt to parse the token as a numeric value
                Double number = Double.valueOf(key); // Convert the token to a Double
                expressionStack.push(number);       // Push the number to the stack
            } catch (NumberFormatException e) {
                // If the token is not a number, it must be an operator
                // Pop the top two values from the stack (operands)
                double val1 = expressionStack.pop(); // First operand (top of the stack)
                double val2 = expressionStack.pop(); // Second operand (next on the stack)
                double result = operate(key, val2, val1); // Perform the operation (val2 operator val1)
                expressionStack.push(result); // Push the result back onto the stack
            }
        }

        // The final result of the evaluation is at the top of the stack
        return expressionStack.pop();
    }
}
