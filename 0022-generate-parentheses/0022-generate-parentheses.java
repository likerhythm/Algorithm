import java.util.*;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<Set<String>> dp = new ArrayList<>();
        dp.add(new HashSet<>());
        dp.add(new HashSet<>());
        dp.get(1).add("()");

        for (int i = 2; i <= n; i++) {
            Set<String> result = new HashSet<>();
            for (String s : dp.get(i - 1)) {
                result.add("(" + s + ")");
            }
            for (int a = 1; a < i; a++) {
                Set<String> aList = dp.get(a);
                int b = i - a;
                Set<String> bList = dp.get(b);
                for (String aStr : aList) {
                    for (String bStr : bList) {
                        result.add(aStr + bStr);
                        result.add(bStr + aStr);
                    }
                }
            }
            dp.add(result);
        }

        return new ArrayList<>(dp.get(n));
    }
}