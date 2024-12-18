package com.epic;

public class ExpressionUtils
{
    /*
     * Checks if the current expression is a valid mathematical expression
     * @return true if invalid mathematical expression (e.g 5-*3 -> false, 5*-3 -> true)
     * */
    public static boolean isValidSequence(String input)
    {
        return !(
                hasIllegalOperatorSequence(input)
                || hasConsecutiveDecimalPoints(input)
                || hasEmptyParenthesis(input)
                || hasInvalidVocabulary(input)
                || hasUnmatchedParenthesis(input)
                || hasInvalidExponentialOperator(input)
                || hasOperandWithUnmatchedValue(input)
        );
    }

    public static boolean hasIllegalOperatorSequence(String input)
    {
        return (
                input.contains("-*")
                || input.contains("/*")
                || input.contains("*/")
                || input.contains("+*")
                || input.contains("-/")
        );
    }

    public static boolean hasConsecutiveDecimalPoints(String input)
    {
        return input.contains("..");
    }

    public static boolean hasEmptyParenthesis(String input)
    {
        return input.contains("()");
    }

    public static boolean hasInvalidVocabulary(String input)
    {
        return input.matches("[a-zA-Z]");
    }

    public static boolean hasUnmatchedParenthesis(String input)
    {
        ExpressionContextManager ctx = new ExpressionContextManager(input);
        int openCount = 0;
        int closedCount = 0;

        while (ctx.hasNext())
        {
            if (ctx.cur() == '(')
            {
                openCount++;
            }
            else if (ctx.cur() == ')')
            {
                closedCount++;
            }
            ctx.incrementPos();
        }

        return (openCount != closedCount);
    }

    public static boolean hasInvalidExponentialOperator(String input)
    {
        ExpressionContextManager ctx = new ExpressionContextManager(input);

        while (ctx.hasNext())
        {
            if (ctx.cur() == '^' && (!Character.isDigit(ctx.prev()) || !Character.isDigit(ctx.next())))
                return true;
            ctx.incrementPos();
        }
        return false;
    }

    public static boolean hasOperandWithUnmatchedValue(String input)
    {
        ExpressionContextManager ctx = new ExpressionContextManager(input);

        while (ctx.hasNext())
        {
            if (Character.isDigit(ctx.prev()) && (ctx.next() == '-' || ctx.next() == '+'))
                return true;
            ctx.incrementPos();
        }
        return false;
    }
}