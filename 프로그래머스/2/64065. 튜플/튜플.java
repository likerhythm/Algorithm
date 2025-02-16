import java.util.*;

class Solution {
    public int[] solution(String s) {
        
        Map<Integer, Integer> countMap = new HashMap<>();
        
        String removed = s.replaceAll("[{}]", "");
        
        int[] intArr = Arrays.stream(removed.split(",")).mapToInt(Integer::parseInt).toArray();

        for (int i : intArr) {
            countMap.merge(i, 1, Integer::sum);
        }
        
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(countMap.entrySet());
        list.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));
        
        System.out.println(list);
        int[] answer = list.stream().mapToInt(Map.Entry::getKey).toArray();        
        return answer;
    }
}