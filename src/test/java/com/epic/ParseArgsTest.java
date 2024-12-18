package com.epic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


class ParseArgsTest
{
    @Test
    void testIsValidExpression() {
        String input = "(1.9 - 5) * -5^3";
        Parser parser = new Parser();
        List<String> expected = Arrays.asList("(", "1.9", "-", "5", ")", "*", "-5", "^", "3");
        List<String> actual = parser.parse(input);
        assertEquals(expected, actual);
    }
    @Test
    void hasInvalidExponentialOperatorNoBase()
    {
        Parser parser = new Parser();
        assertThrows(InvalidExpressionException.class, ()-> parser.parse("^3"));
    }
    @Test
    void hasInvalidExponentialOperatorNoExponent()
    {
        Parser parser = new Parser();
        assertThrows(InvalidExpressionException.class, ()-> parser.parse("3^"));
    }
    @Test
    public void testHasUnmatchedParenthesis()
    {
        Parser parser = new Parser();
        assertThrows(InvalidExpressionException.class, ()-> parser.parse("("));
    }

    @Test
    void testHasSequentialDecimalPoints()
    {
        Parser parser = new Parser();
        assertThrows(InvalidExpressionException.class, ()-> parser.parse(".."));
    }

    @Test
    void testHasEmptyParenthesis()
    {
        Parser parser = new Parser();
        assertThrows(InvalidExpressionException.class, ()-> parser.parse("()"));
    }

    @Test
    void testHasInvalidVocabulary()
    {
        Parser parser = new Parser();
        assertThrows(InvalidExpressionException.class, ()-> parser.parse("a"));
    }

    @Test
    void testHasIllegalOperatorSequenceMinusMultiply()
    {
        Parser parser = new Parser();
        assertThrows(InvalidExpressionException.class, ()-> parser.parse("-*"));
    }

    @Test
    void testHasIllegalOperatorSequenceDivideMultiply()
    {
        Parser parser = new Parser();
        assertThrows(InvalidExpressionException.class, ()-> parser.parse("/*"));
    }

    @Test
    void testHasIllegalOperatorSequenceMultiplyDivide()
    {
        Parser parser = new Parser();
        assertThrows(InvalidExpressionException.class, ()-> parser.parse("*/"));
    }

    @Test
    void testHasIllegalOperatorSequencePlusMultiply()
    {
        Parser parser = new Parser();
        assertThrows(InvalidExpressionException.class, ()-> parser.parse("+*"));
    }

    @Test
    void testHasIllegalOperatorSequenceMinusDivide()
    {
        Parser parser = new Parser();
        assertThrows(InvalidExpressionException.class, ()-> parser.parse("-/"));
    }


    @Test
    void testHasOperandWithUnmatchedValue()
    {
        Parser parser = new Parser();
        assertThrows(InvalidExpressionException.class, ()-> parser.parse("22-"));
    }
}