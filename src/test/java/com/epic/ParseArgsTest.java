package com.epic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ParseArgsTest {
    @Test
    void testExpressionSplitting() {
        String[] test = {"(1.9 - 5) * -5^3"};
        List<String> expected = Arrays.asList("(", "1.9", "-", ")", "*", "-5", "^", "3");
        List<String> actual = ParseArgs.parseArgs(test);
        assertEquals(expected, actual);
    }
}

//    private static void stringTest2(){
//        String[] test = {"(1.9 - 5 * -5^3"};
//        String testOutput = "new exception, incorrect amount of brackets";
//        System.out.println("Test1 Expected Output: " + testOutput + "\nCurrent Output: " + ParseArgs.parseArgs(test));
//    }
//
//    private static void stringTest3(){
//        String[] test = {"(1.9 - * 5) * + 5^3"};
//        String testOutput = "new exception, + and * shouldnt be together";
//        System.out.println("Test1 Expected Output: " + testOutput + "\nCurrent Output: " + ParseArgs.parseArgs(test));
//    }