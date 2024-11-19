import java.util.ArrayList;
import java.util.Arrays;

public class InfixCalculator extends Calculator{
    ArrayList<String> expression;
    //some data to test with
    String[] tempList = new String[]{"5", "+", "(", "-", "2", "*", "5", ")", "/", "1"};
    private final ArrayList<String> tempExpress= (ArrayList<String>) Arrays.asList(tempList);

    public InfixCalculator(ArrayList<String> expression){
        this.expression = expression;
    }


}
