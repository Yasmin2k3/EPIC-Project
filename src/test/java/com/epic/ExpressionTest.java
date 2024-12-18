package com.epic;

import org.junit.jupiter.api.Test;

public class ExpressionTest {
    @Test
    public void test() {
        Expression expression = new Expression("(1.9 - 5) * -5^3 /0");
    }

}
