import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        long[] banIndices = new long[bans.length];
        for (int i = 0; i < bans.length; i++) {
            banIndices[i] = getIndex(bans[i]);
        }
        Arrays.sort(banIndices);

        long left = 1;
        long right = (long) 2e15;
        long answerIndex = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            
            // mid 번호 이하인 금지 주문의 개수 계산
            int bannedCount = countLessEqual(banIndices, mid);
            
            if (mid - bannedCount >= n) {
                answerIndex = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return getString(answerIndex);
    }

    // 문자열을 번호로 변환
    private long getIndex(String s) {
        long res = 0;
        long p = 1;
        for (int i = 1; i < s.length(); i++) {
            p *= 26;
            res += p;
        }
        long val = 0;
        for (char c : s.toCharArray()) {
            val = val * 26 + (c - 'a');
        }
        return res + val + 1;
    }

    // 번호를 문자열로 변환
    private String getString(long n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.append((char) ('a' + (n % 26)));
            n /= 26;
        }
        return sb.reverse().toString();
    }

    // 정렬된 배열에서 target 이하의 값 개수 찾기
    private int countLessEqual(long[] arr, long target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] <= target) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }
}