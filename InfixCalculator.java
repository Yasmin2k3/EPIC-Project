import java.util.ArrayList;
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
                }
                else if(key.equals(")") || key.equals("(")){
                    operatorStack.push(key);
                }
                //if the stack is not empty, and the current operator has greater or equal precidence: push
                else if(!operatorStack.isEmpty() && hasPrecidence(key, operatorStack.peek())){
                    operatorStack.push(key);
                }
                System.out.println("Operator stack head: " + operatorStack.peek() + "");
            }
            else{
                System.out.println("Pushed " + key + " to expression stack");
                expressionStack.push(key);
            }
        }
    }
}
