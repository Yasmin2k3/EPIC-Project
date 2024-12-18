package com.epic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ParseArgsTest {
    @Test
    void testExpressionSplitting() {
        String test = "(1.9 - 5) * -5^3";
        List<String> expected = Arrays.asList("(", "1.9", "-", "5", ")", "*", "-5", "^", "3");
        Parser parser = new Parser();
        List<String> actual = parser.parse(test);
        assertEquals(expected, actual);
    }

    @Test
    void testInvalidExpression() {
        String test = "(1.9 - * 5) * + 5^3";
        Parser parser = new Parser();
        assertThrows(InvalidExpressionError.class, () -> parser.parse(test));
    }
}