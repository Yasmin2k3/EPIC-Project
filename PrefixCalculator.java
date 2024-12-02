import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class PrefixCalculator extends Calculator{
    ArrayList<String> expression;
    ArrayList<String> prefixExpression;

    public PrefixCalculator(ArrayList expression){
        this.expression = expression;
        this.prefixExpression = infixToPrefix();
    }

    //converts infix expression to postfix
    private ArrayList<String> infixToPrefix(){
        Stack<String> operatorStack = new Stack<>();
        Stack<String> expressionStack = new Stack<>();

        for(String key: expression){
            System.out.println("key: " + key);
            //if current key is an operator or a bracket:
            if(key.equals("+") || key.equals("-") || key.equals("/") || key.equals("*") || key.equals("(") || key.equals(")")) {
                if (key.equals("(")) {
                    operatorStack.push(key);
                } else if (key.equals(")")) {
                    while (!Objects.equals(operatorStack.peek(), "(")) {
                        expressionStack.push(operatorStack.pop());
                    }
                    operatorStack.pop();
                } else {
                    //problem where key is always more precident than (
                    while (!operatorStack.isEmpty() && (hasPrecidence(key, operatorStack.peek()))) {
                        expressionStack.push(operatorStack.pop());
                    }
                }
            }
            else{
                expressionStack.push(key);
            }
            System.out.println("Operator stack: " + operatorStack);
            System.out.println("Expression stack: " + expressionStack);
        }

        while (!operatorStack.isEmpty()) {
            expressionStack.push(operatorStack.pop());
        }

        return new ArrayList(expressionStack);
    }

    @Override
    public double calculate(){
        System.out.println(prefixExpression);
        Stack<Double> expressionStack = new Stack<>();

        for(String key: prefixExpression){
            //if what we are currently on can be converted to a double: push it to expression stack
            try{
                Double d = Double.valueOf(key);
                expressionStack.push(d);
            }catch(NumberFormatException e){
                double val1 = expressionStack.pop();
                double val2 = expressionStack.pop();

                expressionStack.push(operate(key, val2, val1));
            }
        }

        //return the final value
        return expressionStack.pop();
    }
}