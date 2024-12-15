import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public abstract class Calculator {
    protected String operators = "+-*^/()";

    public Calculator(){
    }

    //defines order of precedence
    private int precedence(String op1){
        return switch (op1) {
            case "^" -> 3;
            case "*", "/" -> 2;
            case "+", "-" -> 1;
            default -> -1;
            //throw new RuntimeException("Incorrect operator");
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
        return res;
    }

    //Used by both PostfixCalculator and PrefixCalculator.
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

    public abstract double calculate();
}