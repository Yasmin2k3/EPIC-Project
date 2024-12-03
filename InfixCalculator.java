import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Objects;
import java.util.Stack;

public class InfixCalculator extends Calculator{
    private final ArrayList<String> expression;

    public InfixCalculator(ArrayList<String> expression){
        this.expression = expression;
    }

    private void popAndEval(String key, Stack<Double> expressionStack){
        double val1 = expressionStack.pop();
        double val2 = expressionStack.pop();

        expressionStack.push(operate(key, val2, val1));
    }


    @Override
    public double calculate(){
        Stack<String> operatorStack = new Stack<>();
        Stack<Double> expressionStack = new Stack<>();

        for(String key: expression){
            System.out.println("key: " + key);
            //if current key is an operator or a bracket:
            if(key.equals("+") || key.equals("-") || key.equals("/") || key.equals("*") || key.equals("(") || key.equals(")")){
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

        //while our operator stack isnt empty, finish out the rest of the operations
        while (!operatorStack.isEmpty()) {
            popAndEval(operatorStack.pop(), expressionStack);
        }

        return expressionStack.peek();
    }
}