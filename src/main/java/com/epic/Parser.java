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
                    !expression.isValidSequence()
            ) {
                throw new InvalidExpressionException("Invalid expression: " + input);
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
                throw new InvalidExpressionException("Invalid expression: " + input);
            }
        }

        return expression.getItems();
    }

    private static class Expression
    {
        private final List<String> items = new ArrayList<>();
        private final String input;
        private int pos = 0; // index of the current position

        public Expression(String input)
        {
            this.input = input.replaceAll("\\s+", "");
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

        /*
         * Checks if the current expression is a valid mathematical expression
         * @return true if invalid mathematical expression (e.g 5-*3 -> false, 5*-3 -> true)
         * */
        public boolean isValidSequence() {
            if ((
                    cur() == '-' && next() == '*' ||
                            cur() == '+' && next() == '*') ||
                    cur() == '-' && next() == '/' ||
                    cur() == '+' && next() == '/'
            ) {
                return false;
            }
            else
            {
                return true;
            }
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

}