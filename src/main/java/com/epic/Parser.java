package com.epic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class Parser
{
    HashSet<Character> arithmeticOperators = new HashSet<>(Arrays.asList('+', '-', '*', '/', '^'));

    public List<String> parse(String input) throws InvalidExpressionException
    {
        Expression expression = new Expression(input);
        while (expression.hasNext())
        {
            if (
                    expression.cur() == '(' ||
                    expression.cur() == ')')
            {
                expression.consume();
            }
            else if (
                    Character.isDigit(expression.cur()) ||
                    (expression.cur() == '-' && arithmeticOperators.contains(expression.prev()))
            ) {
                expression.consumeNumber();
            }
            else if (
                    arithmeticOperators.contains(expression.cur())
            ) {
                expression.consume();
            }
            else
            {
                char cur = expression.cur();
            }
        }

        return expression.getItems();
    }

}