//TODO: Make abstract
public class Calculator {
    public Calculator(){

    }

    // multiplication and division has less precidence than addition and subtraction
    protected int precidence(String op1){
        int precidence;
        try{
            if (op1.equals("*") || op1.equals("/")){
                return 2;
            } else if (op1.equals("+") || op1.equals("-")) {
                return 1;
            }
        } catch (Exception e){
            System.out.println("Incorrect value for op1 (" + op1 + ") " + e);
        }

        return -1;
    }

    //if the first operator has more precidence or is the same than the second operator, return true
    protected boolean hasPrecidence(String op1, String op2){
        return (precidence(op1) >= precidence(op2));
    }
}
