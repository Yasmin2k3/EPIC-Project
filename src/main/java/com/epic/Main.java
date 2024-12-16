package com.epic;/*
Title?:
   _____      _            _       _
  / ____|    | |          | |     | |
 | |     __ _| | ___ _   _| | __ _| |_ ___  _ __
 | |    / _` | |/ __| | | | |/ _` | __/ _ \| '__|
 | |___| (_| | | (__| |_| | | (_| | || (_) | |
  \_____\__,_|_|\___|\__,_|_|\__,_|\__\___/|_|
 */
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    //some data to test with
    private static ArrayList<String> buildTest(){
        String[] tempList = new String[]{"5", "+", "(", "3", "*", "5", "^", "2", ")", "-", "1", "/", "0"};
        ArrayList<String> tempExpress = new ArrayList<>();
        for (String i: tempList){
            tempExpress.add(i);
        }
        return tempExpress;
    }

    public static void main(String[] args) {
        boolean running = true;

        System.out.printf("   _____      _            _       _\n" +
                "  / ____|    | |          | |     | |\n" +
                " | |     __ _| | ___ _   _| | __ _| |_ ___  _ __\n" +
                " | |    / _` | |/ __| | | | |/ _` | __/ _ \\| '__|\n" +
                " | |___| (_| | | (__| |_| | | (_| | || (_) | |\n" +
                "  \\_____\\__,_|_|\\___|\\__,_|_|\\__,_|\\__\\___/|_|\n\n");

        while(running){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Would you like to evaluate in:\n1) infix(normal)\n2) prefix\n3) postfix?\nPress 1, 2 or 3. Press x to exit: ");

            try {
                String input = scanner.nextLine().strip();

                if (input.equals("x")) {
                    System.out.println("Thank you for using Stacky!");
                    running = false;
                } else if (input.equals("1")) {
                    InfixCalculator infix = new InfixCalculator(args, buildTest());
                    infix.calculate();
                } else if (input.equals("2")){
                    PrefixCalculator prefix = new PrefixCalculator(args, buildTest());
                    prefix.calculate();
                } else if (input.equals("3")){
                    PostfixCalculator postfix = new PostfixCalculator(args, buildTest());
                    postfix.calculate();
                }
            }catch (InputMismatchException | ArithmeticException e){
                System.out.println("Something went wrong: " + e);
            }
        }
//        stringTest1();
//        stringTest2();
//        stringTest3();
    }
}