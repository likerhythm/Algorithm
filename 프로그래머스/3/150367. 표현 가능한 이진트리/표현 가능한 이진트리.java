class Solution {
    
    static int N;
    
    // number를 이진트리로 바꿀 수 있는지 확인하는 법
    // 1. number를 이진수로 바꾼다.
    // 1-1. 만약 그 자체로 이진트리인지 판단한다.(판단 방법은 아래에)
    // 2. 이진트리가 아니라면 이진수의 앞쪽에 0을 하나씩 추가하면서 이진트리인지 확인한다.
    // 2-1. 이진수의 앞쪽에 1을 추가하거나 이진수의 뒤쪽에 0 또는 1을 추가하면 수 자체가 바뀌기 때문에 그러면 안된다.

    // 이진트리인지 판단하는 방법
    // 이진수의 가운데 수가 1이어야 한다(루트 노드)
    // 루트 노드를 기준으로 왼쪽 서브 트리와 오른쪽 서브 트리를 이분탐색하며 mid 값이 11110000 이런 패턴이면 이진 트리이다.
    // 이진수의 가장 오른쪽 수가 1이면 그 왼쪽 수도 1이어야 한다.
    public int[] solution(long[] numbers) {
        
        N = numbers.length; // 테케 개수
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            long num = numbers[i];
            String binary = Long.toBinaryString(num);
            StringBuilder sb = new StringBuilder(binary);
            
            // 노드 개수를 이진수 길이보다 더 큰 자연수 중 2^n - 1꼴로 나타낼 수 있는 가장 작은 자연수로 만들기
            int treeSize = 1;
            while (treeSize < binary.length()) {
                treeSize = treeSize * 2 + 1; 
            }
            sb.insert(0, "0".repeat(treeSize - binary.length()));
            
            // System.out.println(sb);
            
            String s = sb.toString();
            if (s.charAt(s.length() / 2) != '1') { // 루트 노드가 1이 아닌 경우
                answer[i] = 0;
            } else {
                boolean result = isBinaryTree(s, '1', 0, s.length() - 1);
                if (result) answer[i] = 1;
                else answer[i] = 0;
            }
        }

        // "111" => 7
        // "101010" => 42
        // "101" => 5
        // "0101010"
        return answer;
    }
    
    // parent가 0인데 mid가 1인 패턴이 나타나면 false 반환
    static boolean isBinaryTree(String s, char parent, int left, int right) {
        if (left > right) return true;
        
        int mid = (left + right) / 2;
        if (parent == '0' && s.charAt(mid) == '1') return false;
        // System.out.println(mid);
        
        if (!isBinaryTree(s, s.charAt(mid), left, mid - 1)) {
            return false;
        }
        if (!isBinaryTree(s, s.charAt(mid), mid + 1, right)) {
            return false;
        }
        
        return true;
    }
}