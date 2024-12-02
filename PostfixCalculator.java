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
    public ArrayList<String> infixToPostfix(){
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
                    //problem where key is always more precident than (
                    while (!operatorStack.isEmpty() && (hasPrecidence(key, operatorStack.peek()))) {
                        expressionStack.push(operatorStack.pop());
                    }
                    operatorStack.push(key);
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
        Stack<Double> expressionStack = new Stack<>();

        for(int i=0; i<postfixExpression.size(); i++){
            String j = postfixExpression.get(i);
            if(!(j.equals("+") || j.equals("-") || j.equals("/") || j.equals("*"))){
                expressionStack.push(Double.parseDouble(j));
            }
            else{
                double a = expressionStack.pop();
                double b = expressionStack.pop();
                expressionStack.push(operate(j, b, a)); //swapped a and b because that's how stacks work
            }
        }

        return expressionStack.peek();
    }
}

