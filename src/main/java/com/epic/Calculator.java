package com.epic;

import java.util.*;

public abstract class Calculator {
    protected String operators = "+-*^/()";
    protected List<String> infixExpression;
    private boolean skipExitCheck;


    public void setSkipExitCheck(boolean skipExitCheck) {
        this.skipExitCheck = skipExitCheck;
    }

    public Calculator(String argument) {
        try {
            Parser parser = new Parser();
            this.infixExpression = parser.parse(argument);
        } catch (InvalidExpressionException e) {
            throw new InvalidExpressionException("Invalid Expression: " + e);
        }
    }

    //defines order of precedence
    private int precedence(String op1) {
        return switch (op1) {
            case "^" -> 3;
            case "*", "/" -> 2;
            case "+", "-" -> 1;
            default -> -1;
        };
    }

    //return true if first operator has less or equal precedence
    protected boolean hasPrecedence(String op1, String op2) {
        return (precedence(op1) <= precedence(op2));
    }

    //returns a "operator" b
    protected double operate(String op, double a, double b) {
        double res = 0;
        switch (op) {
            case "^" -> res = Math.pow(a, b);
            case "+" -> res = a + b;
            case "-" -> res = a - b;
            case "*" -> res = a * b;
            case "/" -> {
                //testing if dividing by zero
                if (b == 0) {
                    throw new ArithmeticException("attempted to divide by zero");
                } else {
                    res = a / b;
                }
            }
        }

        return res;
    }

    //Used by both PostfixCalculator and PrefixCalculator.
    //Converts an infix expression to a postfix expression using the Shunting Yard Algorithm
    protected ArrayList<String> infixToPostfix(List<String> expression) {
        Stack<String> operatorStack = new Stack<>();
        Stack<String> expressionStack = new Stack<>();

        for (String key : expression) {
            //if current key is an operator or a bracket:
            if (operators.contains(key)) {
                if (key.equals("(")) {
                    operatorStack.push(key);
                } else if (key.equals(")")) {
                    while (!Objects.equals(operatorStack.peek(), "(")) {
                        expressionStack.push(operatorStack.pop());
                    }
                    operatorStack.pop();
                } else {
                    while (!operatorStack.isEmpty() && (hasPrecedence(key, operatorStack.peek()))) {
                        expressionStack.push(operatorStack.pop());
                    }
                    operatorStack.push(key);

                }
            } else {
                expressionStack.push(key);
            }
        }

        while (!operatorStack.isEmpty()) {
            expressionStack.push(operatorStack.pop());
        }

        return new ArrayList<>(expressionStack);
    }

    protected void printStack(String currentElement, Stack<String> operatorStack, Stack<Double> expressionStack) {
        System.out.println("-----------------------------------");
        System.out.println("Current Element: " + currentElement);
        System.out.println("Operator Stack: " + operatorStack);
        System.out.println("Expression Stack: " + expressionStack);
        System.out.println("-----------------------------------");


    }
  
    protected void printStack(String currentElement, Stack<Double> expressionStack) {
        // Delegate to the 3-argument method, passing null for the operatorStack
        printStack(currentElement, null, expressionStack);
    }

    protected void continueOrExit() {
        if (this.skipExitCheck) {
            return;
        }
        System.out.println("Press Enter to continue or type 'x' to exit.");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("x")) {
            System.out.println("Exiting...");
            System.exit(0);
        }
    }


      /*  System.out.println("-----------------------------------");
        System.out.println("Current Element: " + key);
        System.out.println("Expression Stack: " + expressionStack);
        System.out.println("-----------------------------------");
        //a break
        System.out.println("Press Enter to continue or type 'x' to exit.");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
*/



        public abstract double calculate ();
    }

