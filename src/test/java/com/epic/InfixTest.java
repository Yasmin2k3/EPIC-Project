package com.epic;

import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;
public class InfixTest {

    @Test
    void testDivideByZero(){
        String test = "(1.9 - 5) * -5^3 /0";
        InfixCalculator calculator = new InfixCalculator(test);
        assertThrows(ArithmeticException.class, () -> calculator.calculate());
    }

    @Test
    void testWorks(){
        String test = "(1.9 - 5) * 5^3";
        InfixCalculator calculator = new InfixCalculator(test);
        String expected = "-387.5";
        String actual;
        try{
            actual = String.valueOf(calculator.calculate());
        }catch( ArithmeticException | InvalidExpressionException e){
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
        }catch( ArithmeticException | InvalidExpressionException e){
            actual = String.valueOf(e);
        }
        assertEquals(expected, actual);
    }

    @Test
    void singleNumber(){
        String test = "2";
        InfixCalculator calculator = new InfixCalculator(test);
        String expected = "2.0";
        String actual;
        try{
            actual = String.valueOf(calculator.calculate());
        }catch( ArithmeticException | InvalidExpressionException e){
            actual = String.valueOf(e);
        }
        assertEquals(expected, actual);
    }
}
