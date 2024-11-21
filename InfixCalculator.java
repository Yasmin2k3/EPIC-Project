import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class InfixCalculator extends Calculator{
    ArrayList<String> expression;

    //some data to test with
    private ArrayList<String> buildTest(){
        String[] tempList = new String[]{"5", "+", "(", "3", "-", "*", "5", ")", "-", "1"};
        ArrayList<String> tempExpress = new ArrayList<>();

        for (String i: tempList){
            tempExpress.add(i);
        }

        return tempExpress;
    }

    //will need to eventually take parameters:
    //ArrayList<Character> expression
    public InfixCalculator(){
        //this.expression = expression;
        this.expression = buildTest();
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
                    System.out.println("Pushed " + operatorStack.peek() + " to expression stack");
                    expressionStack.push(operatorStack.pop());
                    System.out.println("expression stack: " + expressionStack);
                    System.out.println("operator stack: " + operatorStack);
                }
            }
        }catch (EmptyStackException e){
            System.out.println("Stack is empty");
        }

    }

    //TODO: make private
    //converts infix expression to postfix
    public void infixToPostfix(){
        Stack<String> operatorStack = new Stack<>();
        Stack<String> expressionStack = new Stack<>();

        for(String key: expression){
            System.out.println("Key: " + key);
            //if current key is an operator or a bracket:
            if(key.equals("+") || key.equals("-") || key.equals("/") || key.equals("*") || key.equals("(") || key.equals(")")){
                //push the first operator to stack
                if(operatorStack.isEmpty()){
                    operatorStack.push(key);
                    System.out.println("expression stack: " + expressionStack);
                }
                //if the stack is not empty, and the current operator has greater or equal precidence: push
                else if(!operatorStack.isEmpty() && hasPrecidence(key, operatorStack.peek()) || key.equals("(")){
                    operatorStack.push(key);
                    System.out.println("expression stack: " + expressionStack);
                }
                else if(key.equals(")")){
                    System.out.println("putting operator stack onto expression stack");
                    popOpStack(expressionStack, operatorStack);
                }
                System.out.println("operator stack: " + operatorStack);
            }
            else{
                System.out.println("Pushed " + key + " to expression stack");
                expressionStack.push(key);
                System.out.println("expression stack: " + expressionStack);
                System.out.println("operator stack: " + operatorStack);
            }
        }
        popOpStack(expressionStack, operatorStack);

        System.out.println("\nFinal Expression: " + expressionStack);
    }
}
