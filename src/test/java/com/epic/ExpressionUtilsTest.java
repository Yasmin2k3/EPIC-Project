package com.epic;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class ExpressionUtilsTest {
    @Test
    void testIsValidExpression1()
    {
        assertTrue(ExpressionUtils.isValidExpression("(1.9 - 5) * -5^3"));
    }

    @Test
    void testIsValidExpression2()
    {
        assertTrue(ExpressionUtils.isValidExpression("(1.9 - 5) * -5^3 /0"));
    }
    @Test
    void hasInvalidExponentialOperatorNoBase()
    {
        assertTrue(ExpressionUtils.hasInvalidExponentialOperator("^3"));
    }
    @Test
    void hasInvalidExponentialOperatorNoExponent()
    {
        assertTrue(ExpressionUtils.hasInvalidExponentialOperator("3^"));
    }
    @Test
    public void testHasUnmatchedParenthesis()
    {
        assertTrue(ExpressionUtils.hasUnmatchedParenthesis("("));
    }

    @Test
    void testHasSequentialDecimalPoints()
    {
        assertTrue(ExpressionUtils.hasConsecutiveDecimalPoints(".."));
    }

    @Test
    void testHasEmptyParenthesis()
    {
        assertTrue(ExpressionUtils.hasEmptyParenthesis("()"));
    }

    @Test
    void testHasInvalidVocabulary()
    {
        assertTrue(ExpressionUtils.hasInvalidVocabulary("a"));
    }

    @Test
    void testHasIllegalOperatorSequenceMinusMultiply()
    {
        assertTrue(ExpressionUtils.hasIllegalOperatorSequence("-*"));
    }

    @Test
    void testHasIllegalOperatorSequenceDivideMultiply()
    {
        assertTrue(ExpressionUtils.hasIllegalOperatorSequence("/*"));
    }

    @Test
    void testHasIllegalOperatorSequenceMultiplyDivide()
    {
        assertTrue(ExpressionUtils.hasIllegalOperatorSequence("*/"));
    }

    @Test
    void testHasIllegalOperatorSequencePlusMultiply()
    {
        assertTrue(ExpressionUtils.hasIllegalOperatorSequence("+*"));
    }

    @Test
    void testHasIllegalOperatorSequenceMinusDivide()
    {
        assertTrue(ExpressionUtils.hasIllegalOperatorSequence("-/"));
    }

    @Test
    void testHasOperandWithUnmatchedValue()
    {
        assertTrue(ExpressionUtils.hasOperandWithUnmatchedValue("22-"));
    }

}
