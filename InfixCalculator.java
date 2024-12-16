import java.util.*;

public class InfixCalculator extends Calculator{
    private final List<String> expression;

    public InfixCalculator(List<String> expression){
        this.expression = expression;
    }

    //pops two numbers from our expression stack and operates the top operator on the operator stack.
    private void popAndEval(String key, Stack<Double> expressionStack){
        double val1 = expressionStack.pop();
        double val2 = expressionStack.pop();

        expressionStack.push(operate(key, val2, val1));
    }

    //Evaluates expression using Shunting Yard Algorithm by Dijkstra
    @Override
    public double calculate(){
        System.out.println("Equation: " + expression);

        Stack<String> operatorStack = new Stack<>();
        Stack<Double> expressionStack = new Stack<>();

        for(String key: expression){
            //Checks if current key is one of our operators in Calculator class
            if(operators.contains(key)){
                if(key.equals("(")){
                    operatorStack.push(key);
                }
                else if(key.equals(")")){
                    while(!Objects.equals(operatorStack.peek(), "(")) {
                        popAndEval(operatorStack.pop(), expressionStack);
                    }
                    operatorStack.pop();
                }
                else{
                    while (!operatorStack.isEmpty() && (hasPrecedence(key, operatorStack.peek()))) {
                        popAndEval(operatorStack.pop(), expressionStack);
                    }
                    operatorStack.push(key);
                }
            }
            else{
                expressionStack.push(Double.valueOf(key));
            }

        }

        //while our operator stack isn't empty, finish out the rest of the operations.
        while (!operatorStack.isEmpty()) {
            popAndEval(operatorStack.pop(), expressionStack);
        }

        return expressionStack.peek();
    }
}