import java.util.*;

class Solution {
    
    static class Node {
        int tmpNum, cnt;
        
        Node (int tmpNum, int cnt) {
            this.tmpNum = tmpNum;
            this.cnt = cnt;
        }
    }
    
    static List<List<Integer>> dp = new ArrayList<>();
    
    public int solution(int base, int target) {
        
        dp.add(new ArrayList<>()); // 0개 사용한 경우
        List<Integer> arr = new ArrayList<>(); // 1개 사용한 경우
        arr.add(base);
        dp.add(arr);
        
        if (base == target) {
            return 1;
        }
        
        for (int i=2; i<=8; i++) { // i개 사용한 경우
            List<Integer> arr1 = new ArrayList<>();
            for (int j=1, k=i-j; j<i; j++, k--) {
                int cnt = dp.size();
                
                int d = 0; // 5, 55, 555 등
                for (int l=0; l<cnt; l++) {
                    d += Math.pow(10, l) * base;
                }
                if (d == target) {
                    return cnt;
                }
                arr1.add(d);
                
                for (int a : dp.get(j)) {
                    for (int b : dp.get(k)) {
                        
                        int c = a + b;
                        if (c == target) {
                            return cnt;
                        }
                        arr1.add(c);
                        
                        c = a - b;
                        if (c == target) {
                            return cnt;
                        }
                        arr1.add(c);
                        
                        c = b - a;
                        if (c == target) {
                            return cnt;
                        }
                        arr1.add(c);
                        
                        c = a * b;
                        if (c == target) {
                            return cnt;
                        }
                        arr1.add(c);
                        
                        if (b != 0) {
                            c = a / b;
                            if (c == target) {
                                return cnt;
                            }
                            arr1.add(c);
                        }
                        
                        if (a != 0) {
                            c = b / a;
                            if (c == target) {
                                return cnt;
                            }
                            arr1.add(c);
                        }
                    }
                }
            }
            dp.add(arr1);
        }
        
        System.out.println(dp.size());
        return -1;
    }
}