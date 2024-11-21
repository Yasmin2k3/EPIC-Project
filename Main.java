/*
Title?:
   _____      _            _       _
  / ____|    | |          | |     | |
 | |     __ _| | ___ _   _| | __ _| |_ ___  _ __
 | |    / _` | |/ __| | | | |/ _` | __/ _ \| '__|
 | |___| (_| | | (__| |_| | | (_| | || (_) | |
  \_____\__,_|_|\___|\__,_|_|\__,_|\__\___/|_|


 */

import java.util.ArrayList;

public class Main {
    //some data to test with
    private static ArrayList<String> buildTest(){
        String[] tempList = new String[]{"5", "+", "(", "3", "-", "*", "5", ")", "-", "1"};
        ArrayList<String> tempExpress = new ArrayList<>();

        for (String i: tempList){
            tempExpress.add(i);
        }

        return tempExpress;
    }

    public static void main(String[] args) {
        PostfixCalculator infThing = new PostfixCalculator(buildTest());
    }
}
