package com.epic;

import java.util.*;

public class PostfixCalculator extends Calculator{
    ArrayList<String> postfixExpression;

    public PostfixCalculator(String expression){
        super(expression);
        //transforms the original equation into postfix
        this.postfixExpression = infixToPostfix(infixExpression);
    }

    //Calculates a postfix expression
    @Override
    public double calculate() throws ArithmeticException{
        System.out.println("Postfix equation: " + postfixExpression);
        Stack<Double> expressionStack = new Stack<>();

        for(String key: postfixExpression){
            //counter for continueOrExit
            int count = 0;
            count ++;
            //if what we are currently on can be converted to a double: push it to expression stack
            try{
                Double d = Double.valueOf(key);
                expressionStack.push(d);
            }catch(NumberFormatException e){
                double val1 = expressionStack.pop();
                double val2 = expressionStack.pop();

                expressionStack.push(operate(key, val2, val1));
            }
            printStack(key, expressionStack);
            //if loop is not finished, call continueOrExit
            if(!(count == postfixExpression.size())) {
                continueOrExit();
            }
        }

        //print the final value
        System.out.println("Final value: " + expressionStack.peek());
        System.out.println("-----------------------------------\n\n");
        return expressionStack.pop();
    }
}
//
