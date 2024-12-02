import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class PostfixCalculator extends Calculator{
    ArrayList<String> expression;
    ArrayList<String> postfixExpression;

    public PostfixCalculator(ArrayList expression){
        this.expression = expression;
        this.postfixExpression = infixToPostfix();
    }

    //converts infix expression to postfix
    private ArrayList<String> infixToPostfix(){
        Stack<String> operatorStack = new Stack<>();
        Stack<String> expressionStack = new Stack<>();

        for(String key: expression){
            System.out.println("key: " + key);
            //if current key is an operator or a bracket:
            if(key.equals("+") || key.equals("-") || key.equals("/") || key.equals("*") || key.equals("(") || key.equals(")")){
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
                    while (!operatorStack.isEmpty() && (hasPrecidence(key, operatorStack.peek()))) {
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

        return new ArrayList(expressionStack);
    }

    @Override
    public double calculate(){
        System.out.println(postfixExpression);
        Stack<Double> expressionStack = new Stack<>();

        for(String key: postfixExpression){
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

