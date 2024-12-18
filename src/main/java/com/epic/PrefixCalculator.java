package com.epic;

import java.util.*;

public class PrefixCalculator extends Calculator {
    ArrayList<String> prefixExpression;

    public PrefixCalculator(String expression) {
        super(expression);
        this.prefixExpression = infixToPrefix(infixExpression);
    }

    //Converts infix expression to a prefix one
    private ArrayList<String> infixToPrefix(List<String> expression) {

        //making new reverse expression to keep original arraylist unchanged.
        ArrayList<String> reversedExpression = new ArrayList<>(expression);

        Collections.reverse(reversedExpression);

        //replace ( with ) and ) with (
        for (int i =0; i < expression.size(); i++) {
            String token = reversedExpression.get(i);
            if (token.equals("(")) {
                reversedExpression.set(i, ")");
            } else if (token.equals(")")) {
                reversedExpression.set(i, "(");
            }
        }

        ArrayList<String> prefix = infixToPostfix(reversedExpression);

        //reversing the prefix stack
        Collections.reverse(prefix);
        return prefix;
    }

    //Calculates a prefix expression
    @Override
    public double calculate() {
        System.out.println("Prefix equation: " + prefixExpression);
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

