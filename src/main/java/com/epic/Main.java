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

    public static void main(String[] args) {
        boolean running = true;
        String arg;
        try {
            arg = args[0];
        } catch (ArrayIndexOutOfBoundsException e){
            Scanner equation = new Scanner(System.in);
            System.out.println("Please enter your equation:");
            arg = equation.nextLine();
        }


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
                Calculator mycalculator;

                switch (input) {
                    case "x" -> {
                        System.out.println("Thank you for using Stacky!");
                        running = false;
                    }
                    case "1" -> {
                            mycalculator = new InfixCalculator(arg);
                            System.out.println(mycalculator.calculate());
                    }
                    case "2" -> {
                        mycalculator = new PrefixCalculator(arg);
                        System.out.println(mycalculator.calculate());
                    }
                    case "3" -> {
                        mycalculator = new PostfixCalculator(arg);
                        System.out.println(mycalculator.calculate());
                    }
                    default -> System.out.println("Wrong input, please input 1, 2, 3 or x");
                }
            }catch (InputMismatchException| ArithmeticException | InvalidExpressionException e){
                System.out.println("Something went wrong: " + e);
            }
        }
//        stringTest1();
//        stringTest2();
//        stringTest3();
    }
}