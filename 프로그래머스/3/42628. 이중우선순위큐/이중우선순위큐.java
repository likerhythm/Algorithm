import java.util.*;

class Solution {
    
    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> count = new TreeMap<>();
        
        for (String op : operations) {
            String[] split = op.split(" ");
            int number = Integer.parseInt(split[1]);
            if (split[0].equals("I")) {
                count.merge(number, 1, Integer::sum);
            } else if (!count.entrySet().isEmpty()) {
                if (number == 1) {
                    int max = count.lastKey();
                    remove(max, count);
                } else {
                    int min = count.firstKey();
                    remove(min, count);
                }
            }
        }
        
        if (count.entrySet().isEmpty()) {
            return new int[] {0, 0};
        }
        
        return new int[] {count.lastKey(), count.firstKey()};
    }
    
    void remove(int n, TreeMap<Integer, Integer> count) {
        int c = count.get(n);
        if (c == 1) count.remove(n);
        else count.put(n, c - 1);
    }
}