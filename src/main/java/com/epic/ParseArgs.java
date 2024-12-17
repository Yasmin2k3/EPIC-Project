package com.epic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Tokenizer {
    private final String input;
    private int pos = 0;

    public Tokenizer(String input) {
        this.input = input.replaceAll("\\s+", "");
    }

    public char peek() {
        return input.charAt(pos);
    }

    public boolean hasNext() {
        return (pos < input.length());
    }

    public char consume() {
        return input.charAt(pos++);
    }

    public double consumeNumber() {
        StringBuilder sb = new StringBuilder();
        while (hasNext() && (Character.isDigit(peek()) || peek() == '.')) {
            sb.append(consume());
        }
        return Double.parseDouble(sb.toString());
    }
    }
}

public class ParseArgs {
    private static final HashSet<String> arithmeticOperators = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
    private static final HashSet<String> openClosingParentheses = new HashSet<>(Arrays.asList("(", ")"));

    public static void main (String[] args) {
        String[] test = {"(1.9 - 5) * -5^3"};
        parseArgs(test);
    }

    public static List<String> parseArgs(String[] args) {
        List<Character> charList = splitToCharList(args);
        List<String> expressionSplit = splitExpression(charList);
        return expressionSplit;
    }

    private static List<String> splitExpression(List<Character> expressionChars) {
        List<String> expressionSplit = new ArrayList<>();
        String currentExpression = "";
        for (Character character : expressionChars) {
            String characterAsString = character.toString();
            if (arithmeticOperators.contains(characterAsString) | openClosingParentheses.contains(characterAsString)) {
                expressionSplit.add(currentExpression);
                currentExpression = "";
                expressionSplit.add(characterAsString);
            }
            else {
                currentExpression += characterAsString;
            }
        }
        return expressionSplit;
    }

    private static List<Character> splitToCharList(String[] stringArray) {
        List<Character> charList = new ArrayList<>();
        for (String str : stringArray) {
            for (char ch : str.toCharArray()) {
                if (ch != ' ')
                    charList.add(ch);
            }
        }

        return charList;
    }
}