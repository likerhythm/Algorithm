class Solution {
    public int solution(int n, int w, int num) {    
        // i번째 줄 -> i + 1번째 줄로 한 층 이동할 때 두 값의 합이 (i * 2 * w + 1)이어야 한다
        // num이 몇 번째 출인가? -> num을 w로 나눈 몫 + 1
        
        int line = num / w + 1;
        if (num % w == 0) line--;
        int top = num;
        int answer = 0;
        System.out.println("line = " + line);
        while (true) {
            answer++;
            top = line * 2 * w + 1 - top;
            System.out.println(top);
            if (top > n) break;
            line++;
        }
        
        return answer;
    }
}