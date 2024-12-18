package com.epic;

import java.util.ArrayList;
import java.util.List;

public class Expression
{
    private final List<String> items = new ArrayList<>();
    private final String input;
    private int pos = 0; // index of the current position

    public Expression(String input) throws InvalidExpressionException
    {
        if (ExpressionUtils.isValidSequence(input))
            this.input = input.replaceAll("\\s+", "");
        else throw new InvalidExpressionException("Invalid expression: " + input);
    }

    /*
     * Get the char at current pos
     * */
    public char cur()
    {
        return input.charAt(pos);
    }

    /*
     * Check if at the end of input
     * @return true if input data exists at index poss++ else false
     * */
    public boolean hasNext()
    {
        return (pos < input.length());
    }

    public char next()
    {
        int posNextIndex = pos + 1;
        return input.charAt(posNextIndex);
    }

    public char prev()
    {
        try {
            int prevIndex = pos - 1;
            return input.charAt(prevIndex);
        } catch (IndexOutOfBoundsException e) {
            return '\0';
        }
    }

    /*
     * Add item at current pos to items, then increment pos by 1
     * */
    public void consume()
    {
        int posNextIndex = pos + 1;
        items.add(input.substring(pos, posNextIndex));
        pos++;
    }

    /*
     * Can consume doubles that are signed, unsigned, decimal fractions, or all
     * */
    public void consumeNumber()
    {
        StringBuilder sb = new StringBuilder();
        if (cur() == '-') { // if num is signed, consume
            sb.append(cur());
            pos++;
        }
        while (hasNext() && (Character.isDigit(cur()) || cur() == '.')) {
            sb.append(cur());
            pos++;
        }
        items.add(sb.toString());
    }

    public List<String> getItems()
    {
        return items;
    }
}
