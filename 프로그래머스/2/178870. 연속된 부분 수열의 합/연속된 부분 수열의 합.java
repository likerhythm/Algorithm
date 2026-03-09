class Solution {
    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;
        int start = 0, end = 0;
        int minLength = n;
        int[] answer = new int[] {0, n - 1};
        
        int sum = sequence[0];
        while (start <= end && end < n) {
            if (sum == k) {
                int length = end - start + 1;
                if (minLength > length) {
                    minLength = length;
                    answer = new int[] {start, end};
                }
                sum -= sequence[start++];
            } else if (sum < k) {
                end += 1;
                if (end < n) {
                    sum += sequence[end];
                }
            } else {
                sum -= sequence[start++];
            }
        }
        
        return answer;
    }
}