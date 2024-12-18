package com.epic;

import java.util.ArrayList;
import java.util.List;

public class Expression extends ExpressionCursor
{
    private final List<String> items = new ArrayList<>();

    public Expression(String input) throws InvalidExpressionException
    {
        super(input);
    }

    /*
     * Add item at current pos to items, then increment pos by 1
     * */
    public void consume()
    {
        items.add(String.valueOf(super.cur()));
        super.incrementCur();
    }

    /*
     * Can consume doubles that are signed, unsigned, decimal fractions, or all
     * */
    public void consumeNumber()
    {// 2 + -3.55
        StringBuilder sb = new StringBuilder();
        if (cur() == '-') { // if num is signed, consume
            sb.append(super.cur());
            super.incrementCur();
        }
        while (hasNext() && (Character.isDigit(cur()) || cur() == '.')) {
            sb.append(super.cur());
            super.incrementCur();
        }
        items.add(sb.toString());
    }

    public List<String> getItems()
    {
        return items;
    }
}