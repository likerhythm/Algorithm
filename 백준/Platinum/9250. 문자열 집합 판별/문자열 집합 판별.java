import java.io.*;
import java.util.*;

public class Main {

	static final int SIZE = 26; // a ~ z
	static class TrieNode{
		boolean output; // 현재 노드에서 pattern 하나가 끝나는지 여부
		Map<Character, TrieNode> child = new HashMap<>(); // 자식 노드들
		TrieNode fail; // 매칭 실패 시 이동할 노드
		public TrieNode() {}
		
		// pattern(ex. 금칙어) 추가하기
		public void insert(String word) { 
			TrieNode curNode = this; // 현재 노드
			for(int i=0; i<word.length(); i++) {
				char c = word.charAt(i);
				
				curNode.child.putIfAbsent(c, new TrieNode()); // 자식 노드 중 문자 c가 없다면 노드 추가
				curNode = curNode.child.get(c); // 문자 c 노드로 이동 
				
				if(i== word.length() - 1) { // 마지막 문자라면 현재 노드의 output을 true로 설정
					curNode.output = true;
				}
			}
		}
		
		// 실패 함수 계산(실패 시 이동할 노드 계산) - bfs로 수행
		public void computeFailFunc() {
			Queue<TrieNode> q = new LinkedList<>(); // bfs queue
			this.fail = this; // root의 실패 노드를 root로 초기화
			q.add(this);
			
			while (!q.isEmpty()) { // bfs 수행
				TrieNode cur = q.poll();
				for (int i=0; i<SIZE; i++) {
					char c = (char)(i+97);
					
					TrieNode nxt = cur.child.get(c); // 문자 c인 자식 get
					if (nxt ==null) continue; // 그런 자식이 없는 경우 건너뜀
					
					if (cur == this) { // poll한 노드가 root인 경우(여기서 this는 root)
						nxt.fail = this;
					} else {
						TrieNode failLinkNode = cur.fail;
						
						// 실패 링크를 따라가며 자식 노드 중 c를 가진 노드 찾기
						while (failLinkNode != this && failLinkNode.child.get(c) == null) {
							failLinkNode = failLinkNode.fail;
						}
						
						// while문에서 해당 노드를 찾았다면
						// 실패 링크를 해당 노드의 자식 노드 중 c인 노드로 설정
						if (failLinkNode.child.get(c) != null) {
							failLinkNode = failLinkNode.child.get(c);
						}
						nxt.fail = failLinkNode;
					}
					
					// 실패 링크가 
					if (nxt.fail.output) {
						nxt.output =true;
					}
					q.add(nxt);
				}
			}
		}
		
		// 아호코라식
		public boolean ahoCorasick(String word) {
			TrieNode curNode = this;
			for(int i=0; i<word.length(); i++) {
				char c = word.charAt(i);
				while(curNode != this && curNode.child.get(c) ==null) {
					curNode = curNode.fail;
				}
				if(curNode.child.get(c)!=null) {
					curNode = curNode.child.get(c);
				}
				
				// 금칙어의 유무만 판단
				if(curNode.output) {
					return true;
				}
			}
			return false;
			
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		TrieNode trieSet = new TrieNode();
		for(int i=0; i<n; i++) {
			trieSet.insert(br.readLine());
		}
		
		trieSet.computeFailFunc();
		
		StringBuilder sb = new StringBuilder();
		int q = Integer.parseInt(br.readLine());
		for(int i=0; i<q; i++) {
			if(trieSet.ahoCorasick(br.readLine())) {
				sb.append("YES\n");
			}else {
				sb.append("NO\n");
			}
		}
		System.out.println(sb.toString());
	}
}