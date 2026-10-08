import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {

        Set<String> set = new HashSet<>();

        int left = 0;
        int right = 0;

        // Find extra brackets to remove
        for (char c : s.toCharArray()) {

            if (c == '(') {
                left++;
            } 
            else if (c == ')') {

                if (left > 0) {
                    left--;
                } 
                else {
                    right++;
                }
            }
        }

        backtrack(s, 0, left, right, 0, 0,
                  new StringBuilder(), set);

        return new ArrayList<>(set);
    }

    private void backtrack(String s,
                            int index,
                            int removeLeft,
                            int removeRight,
                            int open,
                            int close,
                            StringBuilder current,
                            Set<String> set) {

        // End of string
        if (index == s.length()) {

            if (removeLeft == 0 &&
                removeRight == 0 &&
                open == close) {

                set.add(current.toString());
            }

            return;
        }

        char c = s.charAt(index);

        // Option 1: Remove current '('
        if (c == '(' && removeLeft > 0) {

            backtrack(
                s,
                index + 1,
                removeLeft - 1,
                removeRight,
                open,
                close,
                current,
                set
            );
        }

        // Option 2: Remove current ')'
        if (c == ')' && removeRight > 0) {

            backtrack(
                s,
                index + 1,
                removeLeft,
                removeRight - 1,
                open,
                close,
                current,
                set
            );
        }

        // Option 3: Keep current character

        current.append(c);

        if (c == '(') {

            backtrack(
                s,
                index + 1,
                removeLeft,
                removeRight,
                open + 1,
                close,
                current,
                set
            );

        } 
        else if (c == ')') {

            // Only keep ')' if there is an unmatched '('
            if (open > close) {

                backtrack(
                    s,
                    index + 1,
                    removeLeft,
                    removeRight,
                    open,
                    close + 1,
                    current,
                    set
                );
            }

        } 
        else {

            // Normal character
            backtrack(
                s,
                index + 1,
                removeLeft,
                removeRight,
                open,
                close,
                current,
                set
            );
        }

        current.deleteCharAt(current.length() - 1);
    }
}