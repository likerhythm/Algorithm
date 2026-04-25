class Solution {
    
    public int solution(int[][] sizes) {
        int garoMax = 0;
        int seroMax = 0;
        // 가로 길이가 세로 길이보다 더 크도록 모두 회전
        for (int[] size : sizes) {
            int garo = size[0];
            int sero = size[1];
            if (garo < sero) {
                size[0] = sero;
                size[1] = garo;
            }
            garoMax = Math.max(garoMax, size[0]);
            seroMax = Math.max(seroMax, size[1]);
        }
        
        return garoMax * seroMax;
    }
}