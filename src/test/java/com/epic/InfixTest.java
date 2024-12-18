package com.epic;

import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;
public class InfixTest {

    @Test
    void testDivideByZero(){
        String test = "(1.9 - 5) * -5^3 /0";
        InfixCalculator calculator = new InfixCalculator(test);
        String expected = "java.lang.ArithmeticException: attempted to divide by zero";
        String actual;
        try{
            actual = String.valueOf(calculator.calculate());
        }catch(InputMismatchException | ArithmeticException | InvalidExpressionException e){
            actual = String.valueOf(e);
        }
        assertEquals(expected, actual);
    }

    @Test
    void testWorks(){
        String test = "(1.9 - 5) * 5^3";
        InfixCalculator calculator = new InfixCalculator(test);
        String expected = "-387.5";
        String actual;
        try{
            actual = String.valueOf(calculator.calculate());
        }catch(InputMismatchException | ArithmeticException | InvalidExpressionException e){
            actual = String.valueOf(e);
        }
        assertEquals(expected, actual);
    }

    @Test
    void testWorksWithNegativeNumber(){
        String test = "(1.9 - 5) * -5^3";
        InfixCalculator calculator = new InfixCalculator(test);
        String expected = "387.5";
        String actual;
        try{
            actual = String.valueOf(calculator.calculate());
        }catch(InputMismatchException | ArithmeticException | InvalidExpressionException e){
            actual = String.valueOf(e);
        }
        assertEquals(expected, actual);
    }
}
