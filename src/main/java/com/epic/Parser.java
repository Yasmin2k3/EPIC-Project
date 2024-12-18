package com.epic;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Parser
{
    HashSet<Character> arithmeticOperators = new HashSet<>(Arrays.asList('+', '-', '*', '/'));

    public List<String> parse(String input) throws InvalidExpressionError
    {
        ExpressionParser tokenizer = new ExpressionParser(input);
        while (tokenizer.hasNext())
        {
            if (
                    tokenizer.cur() == '(' ||
                    tokenizer.cur() == ')')
            {
                tokenizer.consume();
            }
            else if (
                !tokenizer.isValidSequence()
            ) {
                throw new InvalidExpressionError("Invalid expression: " + input);
            }
            else if (
                    Character.isDigit(tokenizer.cur()) ||
                    (tokenizer.cur() == '-' && arithmeticOperators.contains(tokenizer.prev()))
            ) {
                tokenizer.consumeNumber();
            }
            else if (
                    arithmeticOperators.contains(tokenizer.cur())
            ) {
                tokenizer.consume();
            }
            else
            {
                tokenizer.consume();
            }
        }

        return tokenizer.getItems();
    }
}

class InvalidExpressionError extends RuntimeException
{
    InvalidExpressionError(String message)
    {
        super(message);
    }
}