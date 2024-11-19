import java.util.ArrayList;

public class InfixCalculator extends Calculator{
    ArrayList<Character> expression;

    //some data to test with
    private ArrayList<Character> buildTest(){
        char[] tempList = new char[]{'5', '+', '(', '-', '2', '*', '5', ')', '/', '1'};
        ArrayList<Character> tempExpress = new ArrayList<>();

        for (char i: tempList){
            tempExpress.add(i);
        }

        return tempExpress;
    }

    public InfixCalculator(ArrayList<Character> expression){
        //this.expression = expression;
        this.expression = buildTest();
    }
}
