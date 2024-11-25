import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class PostfixCalculator extends Calculator{
    ArrayList<String> expression;
    ArrayList<String> postfixExpression;
    double result; //TODO: change this to somewhere else

    //will need to eventually take parameters:
    //ArrayList<Character> expression
    public PostfixCalculator(ArrayList expression){
        this.expression = expression;
        this.postfixExpression = infixToPostfix();
    }

    private void popOpStack(Stack expressionStack, Stack operatorStack){
        if (operatorStack == null){
            System.out.println("Operator stack cannot be null");
            return;
        }
        try{
            while(!operatorStack.isEmpty()){
                if(operatorStack.peek() == "("){
                    operatorStack.pop();
                }
                else{
                    expressionStack.push(operatorStack.pop());
                }
            }
        }catch (EmptyStackException e){
            System.out.println("Stack is empty");
        }

    }

    //converts infix expression to postfix
    private ArrayList<String> infixToPostfix(){
        Stack<String> operatorStack = new Stack<>();
        Stack<String> expressionStack = new Stack<>();

        for(String key: expression){
            //if current key is an operator or a bracket:
            if(key.equals("+") || key.equals("-") || key.equals("/") || key.equals("*") || key.equals("(") || key.equals(")")){
                //push the first operator to stack
                if(operatorStack.isEmpty()){
                    operatorStack.push(key);
                }
                //if the stack is not empty, and the current operator has greater or equal precidence: push
                else if(!operatorStack.isEmpty() && hasPrecidence(key, operatorStack.peek()) || key.equals("(")){
                    operatorStack.push(key);
                }
                else if(key.equals(")")){
                    popOpStack(expressionStack, operatorStack);
                }
            }
            else{
                expressionStack.push(key);
            }
        }
        popOpStack(expressionStack, operatorStack);

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

