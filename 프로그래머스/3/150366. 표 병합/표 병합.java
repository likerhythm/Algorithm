import java.util.*;

// UPDATE r c value
// (r, c)의 루트 노드를 찾아서 그 값을 value로 바꾼다.

// UPDATE value1 value2
// 모든 루트 노드를 탐색하며 value1을 value2로 바꾼다.

// MERGE r1 c1 r2 c2
// (r2, c2)의 루트 노드를 (r1, c1)의 루트 노드에 병합한다.

// UNMERGE r c
// (r, c)의 루트 노드를 찾고 해당 트리에 속하는 모든 노드들의 루트노드를 자기 자신으로 설정한다

// PRING r c
// (r, c)의 루트 노들르 찾고 값을 출력한다. 값이 없다면 "EMPTY"를 출력한다.

class Solution {
    
    static class Node {
        int num;
        String value;
        
        public Node(int num, String value) {
            this.num = num;
            this.value = value;
        }
    }
    
    static int[] parent;
    static int[] rank;
    static Node[] nodes;
    static final int nodeCnt = 2500;
    
    public String[] solution(String[] commands) {
        nodes = new Node[nodeCnt];
        for (int i = 0; i < nodeCnt; i++) {
            nodes[i] = new Node(i, "");
        }
        
        parent = new int[nodeCnt];
        for (int i = 0; i < nodeCnt; i++) {
            parent[i] = i;
        }
        
        rank = new int[nodeCnt];
        Arrays.fill(rank, 1);
        
        List<String> answerList = new LinkedList<>();
        for (String command : commands) {
            String[] split = command.split(" ");
            String cmd = split[0];
            
            if (cmd.equals("UPDATE")) {
                if (split.length == 4) {
                    int r = Integer.parseInt(split[1]);
                    int c = Integer.parseInt(split[2]);
                    String value = split[3];
                    
                    int num = getNum(r, c);
                    int root = findRoot(num);
                    nodes[root].value = value;
                } else if (split.length == 3) {
                    String value1 = split[1];
                    String value2 = split[2];
                    for (int num = 0; num < nodeCnt; num++) {
                        if (parent[num] != num) continue;
                        if (nodes[num].value.equals(value1)) {
                            nodes[num].value = value2;
                        }
                    }
                }
                
            } else if (cmd.equals("MERGE")) {
                int r1 = Integer.parseInt(split[1]);
                int c1 = Integer.parseInt(split[2]);
                int r2 = Integer.parseInt(split[3]);
                int c2 = Integer.parseInt(split[4]);
                int num1 = getNum(r1, c1);
                int num2 = getNum(r2, c2);
                union(num1, num2);
                
            } else if (cmd.equals("UNMERGE")) {
                int r = Integer.parseInt(split[1]);
                int c = Integer.parseInt(split[2]);
                int num = getNum(r, c);
                int root = findRoot(num);
                nodes[num].value = nodes[root].value;
                
                List<Integer> unmergeList = new LinkedList<>();
                for (int n = 0; n < nodeCnt; n++) {
                    if (n == num) continue;
                    int ro = findRoot(n);
                    if (ro == root) {
                        unmergeList.add(n);
                    }
                }
                for (int n : unmergeList) {
                    parent[n] = n;
                    nodes[n].value = "";
                }
                parent[num] = num;
                
            } else if (cmd.equals("PRINT")) {
                int r = Integer.parseInt(split[1]);
                int c = Integer.parseInt(split[2]);
                int root = findRoot(getNum(r, c));
                if (nodes[root].value.equals("")) {
                    answerList.add("EMPTY");
                } else {
                    answerList.add(nodes[root].value);
                }
            }
        }
        
        String[] answer = new String[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
    
    static int findRoot(int num) {
        if (parent[num] == num) {
            return num;
        }
        
        return parent[num] = findRoot(parent[num]);
    }
    
    static void union(int num1, int num2) {
        int r1 = findRoot(num1);
        int r2 = findRoot(num2);
        
        if (r1 == r2) return;
        
        if (nodes[r1].value.equals("")) {
            parent[r1] = r2;
        } else {
            parent[r2] = r1;
        }
    }
    
    static int getNum(int r, int c) {
        return (r - 1) * 50 + (c - 1);
    }
}