import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ParseArgs {
    private static final HashSet<String> arithmeticOperators = new HashSet<>(Arrays.asList("+", "-", "*", "/"));

    public static List<String> parseArgs(String[] args) {
        List<Character> charList = splitToCharList(args);
        List<String> expressionSplit = splitExpression(charList);
        return expressionSplit;
    }

    private static List<String> splitExpression(List<Character> expressionChars) {
        List<String> expressionSplit = new ArrayList<>();
        String currentExpression = "";
        for (Character character : expressionChars) {
            if (arithmeticOperators.contains(character.toString())) {
                expressionSplit.add(currentExpression);
                currentExpression = "";
                expressionSplit.add(character.toString());
            }
            else {
                currentExpression += character.toString();
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