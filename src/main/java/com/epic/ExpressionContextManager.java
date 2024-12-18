package com.epic;

public class ExpressionContextManager
{
    protected final String input;
    private int pos;

    ExpressionContextManager(String expression) throws InvalidExpressionException
    {
        if (expression != null || expression == "")
            this.input = expression.replaceAll("\\s+", "");
        else
            throw new InvalidExpressionException(expression);
    }

    public char next()
    {
        int nextPos = this.pos + 1;
        if (nextPos >= this.input.length())
            return '\0';
        return input.charAt(nextPos);
    }

    public char cur()
    {
        return input.charAt(pos);
    }

    public char prev()
    {
        int prevPos = this.pos - 1;
        if (prevPos < 0)
            return '\0';
        return input.charAt(prevPos);
    }

    public boolean hasNext()
    {
        return (this.pos < this.input.length());
    }

    public void incrementPos()
    {
        this.pos++;
    }
}