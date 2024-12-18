package com.epic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ParseArgsTest {
    Parser parser = new Parser();

    ParseArgsTest() {
        this.parser = new Parser();
    }

    @Test
    void testExpressionSplitting() {
        String input = "(1.9 - 5) * -5^3";
        List<String> expected = Arrays.asList("(", "1.9", "-", "5", ")", "*", "-5", "^", "3");
        List<String> actual = parser.parse(input);
        assertEquals(expected, actual);
    }

    @Test
    void testInvalidExpression() {
        String input = "(1.9 - * 5) * + 5^3";
        assertThrows(InvalidExpressionException.class, () -> parser.parse(input));
    }

    @Test
    void invalidOperatorPair() {
        String input = "2+/2";
        assertThrows(InvalidExpressionException.class, () -> parser.parse(input));
    }

    @Test
    void invalidDecimalPoint() {
        String input = "2..2";
        assertThrows(InvalidExpressionException.class, () -> parser.parse(input));
    }

    @Test
    void emptyParentheses() {
        String input = "()";
        assertThrows(InvalidExpressionException.class, () -> parser.parse(input));
    }


}