package com.epic;

public class ExpressionContextManager
{
    String inputExpression;
    int pos;

    ExpressionContextManager(String expression) throws IllegalArgumentException
    {
        if (expression != null || expression != "")
            this.inputExpression = expression;
        else
            throw new IllegalArgumentException("Expression can not be null");
    }

    public char next()
    {
        int nextPos = this.pos + 1;
        if (nextPos >= this.inputExpression.length())
            return '\0';
        return inputExpression.charAt(nextPos);
    }

    public char cur()
    {
        return inputExpression.charAt(pos);
    }

    public char prev()
    {
        int prevPos = this.pos - 1;
        if (prevPos < 0)
            return '\0';
        return inputExpression.charAt(prevPos);
    }

    public boolean hasNext()
    {
        return (this.pos < this.inputExpression.length());
    }

    public void incrementPos()
    {
        this.pos++;
    }
}