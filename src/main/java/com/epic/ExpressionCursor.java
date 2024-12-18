package com.epic;

/**
 * Utility class used to step forward through a mathematical expression String.
 * <p/>
 * Utility class for String type mathematical expressions
 * to manipulate and track a current, previous and next position.
 * Also provides ability to change position by 1, in forward direction only.
 * e.g. "2+5+4", current, previous and next could be 5, + and + respectively.
 */
public class ExpressionCursor
{
    protected final String input;
    private int cursor;

    /**
     * Constructor
     * @param expression
     * @throws InvalidExpressionException if expression is null or empty string.
     */
    ExpressionCursor(String expression) throws InvalidExpressionException
    {
        if (expression != null || !expression.trim().equals(""))
        {
            this.input = expression.replaceAll("\\s+", "");
        }
        else {
            throw new InvalidExpressionException(expression);
        }
    }

    /**
     * @return character before cursor
     */
    public char prev()
    {
        int prevPos = this.cursor - 1;
        if (prevPos < 0)
            return '\0';
        return input.charAt(prevPos);
    }

    /**
     * @return current character.
     */
    public char cur()
    {
        return input.charAt(cursor);
    }

    /**
     * Does not increment cursor.
     * @return character after cursor.
     */
    public char next()
    {
        int nextPos = this.cursor + 1;
        if (nextPos >= this.input.length())
            return '\0';
        return input.charAt(nextPos);
    }

    /**
     * @return true if character after cursor exists, otherwise false.
     */
    public boolean hasNext()
    {
        return (this.cursor < this.input.length());
    }

    /**
     * changes cursor to next character in string.
     */
    public void incrementCur()
    {
        this.cursor++;
    }
}