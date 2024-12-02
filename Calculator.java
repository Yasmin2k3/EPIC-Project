public abstract class Calculator {
    public Calculator(){
    }

    // multiplication and division has less precidence than addition and subtraction. Power has top precidence
    protected int precidence(String op1){
        try{
            if(op1.equals("^")){
                return 3;
            }
            else if (op1.equals("*") || op1.equals("/")){
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
        return (precidence(op1) <= precidence(op2));
    }

    //call this in calculate() for example
    //returns a "operator" b
    protected double operate(String op, double a, double b){
        double res = 0;
        switch (op){
            case("+"):
                res = a+b;
                break;
            case("-"):
                res = a-b;
                break;
            case("*"):
                res = a*b;
                break;
            case("/"):
                res = a/b;
                break;
        }
        return res;
    }

    public abstract double calculate();
}