package com.epic;

/**
 * Used to check expressions for invalid mathematical.
 */
public class ExpressionValidationUtils
{
    /*
     * Checks if the current expression is a valid mathematical expression
     * @return true if invalid mathematical expression (e.g 5-*3 -> false, 5*-3 -> true)
     * */
    public static boolean isValidExpression(String input)
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

    protected static boolean hasIllegalOperatorSequence(String input)
    {
        return (
                input.contains("-*")
                || input.contains("/*")
                || input.contains("*/")
                || input.contains("+*")
                || input.contains("-/")
        );
    }

    protected static boolean hasConsecutiveDecimalPoints(String input)
    {
        return input.contains("..");
    }

    protected static boolean hasEmptyParenthesis(String input)
    {
        return input.contains("()");
    }

    protected static boolean hasInvalidVocabulary(String input)
    {
        return input.matches("[a-zA-Z]");
    }

    protected static boolean hasUnmatchedParenthesis(String input)
    {
        ExpressionCursor ctx = new ExpressionCursor(input);
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
            ctx.incrementCur();
        }

        return (openCount != closedCount);
    }

    protected static boolean hasInvalidExponentialOperator(String input)
    {
        ExpressionCursor ctx = new ExpressionCursor(input);

        while (ctx.hasNext())
        {
            if (ctx.cur() == '^' && (!Character.isDigit(ctx.prev()) || !Character.isDigit(ctx.next())))
                return true;
            ctx.incrementCur();
        }
        return false;
    }

    protected static boolean hasOperandWithUnmatchedValue(String input)
    {
        ExpressionCursor ctx = new ExpressionCursor(input);

        while (ctx.hasNext())
        {
            if (Character.isDigit(ctx.prev()) && (ctx.cur() == '-' || ctx.cur() == '+') && (!Character.isDigit(ctx.next())))
                return true;
            ctx.incrementCur();
        }
        return false;
    }
}