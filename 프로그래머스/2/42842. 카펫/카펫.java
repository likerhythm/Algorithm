class Solution {
    public int[] solution(int brown, int yellow) {
        int totalCarpet = brown + yellow;
        int[] answer = new int[2];
        for (int i=1; i<=totalCarpet; i++) {
            if (totalCarpet % i > 0) {
                continue;
            }
            int a = i; // 세로 길이
            int b = totalCarpet / i; // 가로 길이
            if (yellow == (a - 2) * (b - 2) && brown == 2 * a + 2 * b - 4) {
                answer[0] = b;
                answer[1] = a;
                break;
            }
        }
        
        return answer;
    }
}