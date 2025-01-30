import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 이진 검색 트리
public class Main {

    static class Node {
        int key;
        Node left = null;
        Node right = null;

        Node(int key) {
            this.key = key;
        }

        void insert(Node n) {
            if (this.key > n.key) {
                if (this.left == null) {
                    this.left = n;
                } else {
                    this.left.insert(n);
                }
            } else {
                if (this.right == null) {
                    this.right = n;
                } else {
                    this.right.insert(n);
                }
            }
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    // 전위 순회 -> 후위 순회
    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        if (input == null || input.equals("")) {
            return;
        }
        Node rootNode = new Node(Integer.parseInt(input));
        while (true) {
            input = br.readLine();
            if (input == null || input.equals("")) {
                break;
            }
            int key = Integer.parseInt(input);
            Node node = new Node(key);
            rootNode.insert(node);
        }
        printPostorder(rootNode);
    }

    private static void printPostorder(Node node) {
        if (node == null) {
            return;
        }
        printPostorder(node.left);
        printPostorder(node.right);
        System.out.println(node.key);
    }
}
