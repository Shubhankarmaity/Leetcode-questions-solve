import java.util.*;

class Solution {

    private String s;
    private int index;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        index = 0;

        Set<String> result = parseExpression();

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    // expression = term (',' term)*
    private Set<String> parseExpression() {
        Set<String> result = parseTerm();

        while (index < s.length() && s.charAt(index) == ',') {
            index++; // skip ','

            Set<String> next = parseTerm();
            result.addAll(next);
        }

        return result;
    }

    // term = factor factor*
    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length()
                && s.charAt(index) != ','
                && s.charAt(index) != '}') {

            Set<String> factor = parseFactor();

            result = multiply(result, factor);
        }

        return result;
    }

    // factor = letter | '{' expression '}'
    private Set<String> parseFactor() {
        if (s.charAt(index) == '{') {
            index++; // skip '{'

            Set<String> result = parseExpression();

            index++; // skip '}'

            return result;
        }

        // lowercase letter
        Set<String> result = new HashSet<>();
        result.add(String.valueOf(s.charAt(index)));

        index++;

        return result;
    }

    // Cartesian product / concatenation
    private Set<String> multiply(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}