import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class PostfixCalculator extends Calculator{
    ArrayList<String> expression;
    Stack<String> postfixExpression;
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
    private Stack<String> infixToPostfix(){
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

        return expressionStack;
    }

    //make recursive???
    @Override
    public void calculate(){
        //if postfix expression isnt empty, calculate.
        //create duplicate temp stack
        //first expression always will be an operator
        //if (head is operator):
        // calculate
        if(!postfixExpression.isEmpty()){
            Object i = postfixExpression.peek();
            if (!(i.equals("+") ||i.equals("-") || i.equals("*") || i.equals("/"))){
                double a = Double.parseDouble(postfixExpression.pop());
                double b = Double.parseDouble(postfixExpression.pop());
                System.out.println(a);
                System.out.println(b);
            }
            else{
                System.out.println(postfixExpression.peek());
            }
        }
        //a = parse double b = parse double
        //operate (operator, a, b)
    }
}

