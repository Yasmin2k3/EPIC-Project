package com.epic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ParseArgsTest {
    @Test
    void testEquality() {
        int expected = 5;
        int actual = 2 + 3;
        assertEquals(expected, actual, "The values should be equal");
    }

    @Test
    void testTrueCondition() {
        boolean condition = 5 > 2;
        assertTrue(condition, "The condition should be true");
    }

    @Test
    void testFalseCondition() {
        boolean condition = 2 > 5;
        assertFalse(condition, "The condition should be false");
    }

    @Test
    void testNotNull() {
        String str = "JUnit";
        assertNotNull(str, "The object should not be null");
    }

    @Test
    void testArrayEquality() {
        int[] expectedArray = {1, 2, 3};
        int[] actualArray = {1, 2, 3};
        assertArrayEquals(expectedArray, actualArray, "The arrays should be equal");
    }

    @Test
    void testThrowsException() {
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            int result = 1 / 0;
        });
        assertEquals("/ by zero", exception.getMessage(), "Exception message should match");
    }

}