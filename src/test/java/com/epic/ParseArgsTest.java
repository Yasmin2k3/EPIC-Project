package com.epic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ParseArgsTest {
    Parser parser;

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
    void testInvalidExpression2() {
        String input = "12 / (2 + 1 )";
        List<String> expected = Arrays.asList("(", "12", "^", "2", "+", "1", "^");
        List<String> actual = parser.parse(input);
        assertEquals(expected, actual);
    }

    @Test
    void testUnmatchedParentheses() {
        String input = "(5+3";
        assertThrows(InvalidExpressionException.class, () -> parser.parse(input));
    }
}