import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class PostfixCalculator extends Calculator{
    List<String> expression;
    ArrayList<String> postfixExpression;

    public PostfixCalculator(List<String> expression){
        this.expression = expression;
        this.postfixExpression = infixToPostfix(expression);
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

