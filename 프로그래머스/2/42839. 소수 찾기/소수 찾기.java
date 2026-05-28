import java.util.*;

class Solution {
    
    int MAX = 10_000_000;
    Set<Integer> primes = new HashSet<>();
    Set<Integer> checkedPrimes = new HashSet<>();
    boolean[] visited;
    int answer;
    
    public int solution(String numbers) {
        MAX = (int) Math.pow(10, numbers.length());
        boolean[] isPrime = new boolean[MAX];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        
        for (int i = 2; i < MAX; i++) {
            if (!isPrime[i]) continue;
            primes.add(i);
            for (int j = i * 2; j < MAX; j += i) {
                isPrime[j] = false;
            }
        }
        
        visited = new boolean[numbers.length()];
        search(numbers, new StringBuilder());
        
        return answer;
    }
    
    void search(String numbers, StringBuilder sb) {
        if (checkPrime(sb)) {
            answer++;
            String s = sb.toString();
            int value = Integer.parseInt(s);
            checkedPrimes.add(value);
        }
        
        for (int i = 0; i < numbers.length(); i++) {
            if (visited[i]) continue;
            char c = numbers.charAt(i);
            visited[i] = true;
            sb.append(c);
            search(numbers, sb);
            visited[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    boolean checkPrime(StringBuilder sb) {
        String s = sb.toString();
        if (s.isEmpty()) return false;
        if (s.charAt(0) == '0') return false;
        int value = Integer.parseInt(s);
        if (checkedPrimes.contains(value)) return false;
        return primes.contains(value);
    }
}