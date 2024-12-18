package com.epic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

public abstract class Calculator {
    protected String operators = "+-*^/()";

    public Calculator(String[] args){
    }

    //defines order of precedence
    private int precedence(String op1){
        return switch (op1) {
            case "^" -> 3;
            case "*", "/" -> 2;
            case "+", "-" -> 1;
            default -> -1;
        };
    }

    //return true if first operator has less or equal precedence
    protected boolean hasPrecedence(String op1, String op2){
        return (precedence(op1) <= precedence(op2));
    }

    //returns a "operator" b
    protected double operate(String op, double a, double b){
        double res = 0;
        switch (op){
            case"^" -> res = Math.pow(a, b);
            case"+" -> res = a+b;
            case"-" -> res = a-b;
            case"*" -> res = a*b;
            case"/" -> res = a/b;
        }
        //testing if dividing by zero
        if (res == NEGATIVE_INFINITY || res == POSITIVE_INFINITY){
            throw new ArithmeticException("attempted to divide by zero");
        }
        //testing for overflow
        if (res == Double.MAX_VALUE){
            throw new RuntimeException("Value equals maximum value of a double. This will likely result in an incorrect calculation");
        }
        //testing for underflow
        else if (res == Double.MIN_VALUE || a == Double.MIN_VALUE || b == Double.MIN_VALUE){
            throw new RuntimeException("Value equals minimum value of a double. This will likely result in an incorrect calculation");
        }

        return res;
    }

    //Used by both PostfixCalculator and PrefixCalculator.
    //Converts an infix expression to a postfix expression using the Shunting Yard Algorithm
    protected ArrayList<String> infixToPostfix(List<String> expression){
        Stack<String> operatorStack = new Stack<>();
        Stack<String> expressionStack = new Stack<>();

        for(String key: expression){
            //if current key is an operator or a bracket:
            if(operators.contains(key)){
                if(key.equals("(")){
                    operatorStack.push(key);
                }
                else if(key.equals(")")){
                    while(!Objects.equals(operatorStack.peek(), "(")) {
                        expressionStack.push(operatorStack.pop());
                    }
                    operatorStack.pop();
                }
                else{
                    while (!operatorStack.isEmpty() && (hasPrecedence(key, operatorStack.peek()))) {
                        expressionStack.push(operatorStack.pop());
                    }
                    operatorStack.push(key);

                }
            }
            else{
                expressionStack.push(key);
            }
        }

        while (!operatorStack.isEmpty()) {
            expressionStack.push(operatorStack.pop());
        }

        return new ArrayList<>(expressionStack);
    }
    
    protected void printStack(String currentElement, Stack<String> operatorStack, Stack<Double> expressionStack) {
        System.out.println("Current Element: " + currentElement);
        System.out.println("Operator Stack: " + operatorStack);
        System.out.println("Expression Stack: " + expressionStack);
        System.out.println("-----------------------------------");
    }

    public void printCalculation(String key, Stack<String> operatorStack, Stack<String> expressionStack){
    }

    public abstract double calculate();
}