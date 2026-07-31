class Solution {
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0, right = 0;
        char[] chars = s.toCharArray();
        int answer = 0;

        while (true) {
            while (right < chars.length) {
                char rc = chars[right];
                if (set.contains(rc))
                    break;
                set.add(rc);
                right++;
            }
            answer = Math.max(answer, right - left);
            
            if (right == chars.length) {
                break;
            }

            while (true) {
                char lc = chars[left++];
                set.remove(lc);
                if (lc == chars[right])
                    break;
            }
        }

        return answer;
    }
}