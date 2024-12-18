package com.epic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Parser
{
    HashSet<Character> arithmeticOperators = new HashSet<>(Arrays.asList('+', '-', '*', '/', '^'));

    public List<String> parse(String input) throws InvalidExpressionError
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
                !expression.isValidSequence()
            ) {
                throw new InvalidExpressionError("Invalid expression: " + input);
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
                System.out.println("Couldnt parse expression: " + expression.cur());
            }
        }

        return expression.getItems();
    }
}

class InvalidExpressionError extends RuntimeException
{
    InvalidExpressionError(String message)
    {
        super(message);
    }
}