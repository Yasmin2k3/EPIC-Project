package com.epic;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class ExpressionValidationUtilsTest {
    @Test
    void testIsValidExpression1()
    {
        assertTrue(ExpressionValidationUtils.isValidExpression("(1.9 - 5) * -5^3"));
    }

    @Test
    void testIsValidExpression2()
    {
        assertTrue(ExpressionValidationUtils.isValidExpression("(1.9 - 5) * -5^3 /0"));
    }
    @Test
    void hasInvalidExponentialOperatorNoBase()
    {
        assertTrue(ExpressionValidationUtils.hasInvalidExponentialOperator("^3"));
    }
    @Test
    void hasInvalidExponentialOperatorNoExponent()
    {
        assertTrue(ExpressionValidationUtils.hasInvalidExponentialOperator("3^"));
    }
    @Test
    public void testHasUnmatchedParenthesis()
    {
        assertTrue(ExpressionValidationUtils.hasUnmatchedParenthesis("("));
    }

    @Test
    void testHasSequentialDecimalPoints()
    {
        assertTrue(ExpressionValidationUtils.hasConsecutiveDecimalPoints(".."));
    }

    @Test
    void testHasEmptyParenthesis()
    {
        assertTrue(ExpressionValidationUtils.hasEmptyParenthesis("()"));
    }

    @Test
    void testHasInvalidVocabulary()
    {
        assertTrue(ExpressionValidationUtils.hasInvalidVocabulary("a"));
    }

    @Test
    void testHasIllegalOperatorSequenceMinusMultiply()
    {
        assertTrue(ExpressionValidationUtils.hasIllegalOperatorSequence("-*"));
    }

    @Test
    void testHasIllegalOperatorSequenceDivideMultiply()
    {
        assertTrue(ExpressionValidationUtils.hasIllegalOperatorSequence("/*"));
    }

    @Test
    void testHasIllegalOperatorSequenceMultiplyDivide()
    {
        assertTrue(ExpressionValidationUtils.hasIllegalOperatorSequence("*/"));
    }

    @Test
    void testHasIllegalOperatorSequencePlusMultiply()
    {
        assertTrue(ExpressionValidationUtils.hasIllegalOperatorSequence("+*"));
    }

    @Test
    void testHasIllegalOperatorSequenceMinusDivide()
    {
        assertTrue(ExpressionValidationUtils.hasIllegalOperatorSequence("-/"));
    }

    @Test
    void testHasOperandWithUnmatchedValue()
    {
        assertTrue(ExpressionValidationUtils.hasOperandWithUnmatchedValue("22-"));
    }

}
