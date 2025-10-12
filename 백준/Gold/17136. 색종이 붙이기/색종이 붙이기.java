import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	static int[][] arr;
	static int answer = Integer.MAX_VALUE;
	static boolean[][] visited;
	static int[] paperCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[10][10];
		for (int i = 0; i < 10; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		visited = new boolean[10][10];
		paperCnt = new int[6];
		
		backtracking(0, 0, 0);
		
		if (answer == Integer.MAX_VALUE) answer = -1;
		System.out.println(answer);
	}
	
	public static void backtracking(int n, int m, int cnt) {
		if (cnt > answer) {
			return;
		}
		
		if (n == 10) {
//			System.out.println("끝 cnt = " + cnt);
			answer = Math.min(answer, cnt);
			return;
		}
		
		if (arr[n][m] == 0 || visited[n][m]) { // 1이 아니거나 이미 색종이로 덮힌 경우
			int nn = n, nm = m + 1;
			if (m == 9) {
				nn = n + 1;
				nm = 0;
			}
			backtracking(nn, nm, cnt);
		} else {
			for (int size = 5; size >= 1; size--) {
				if (paperCnt[size] == 5) continue;
				if (n + size > 10) continue;
				if (m + size > 10) continue;
				
				boolean canMake = true;
				outer : for (int i = n; i < n + size; i++) {
					for (int j = m; j < m + size; j++) {
						if (arr[i][j] == 0 || visited[i][j]) {
							canMake = false;
							break outer;
						}
					}
				}
				
				if (!canMake) continue;
				
				paperCnt[size]++;
				for (int i = n; i < n + size; i++) {
					for (int j = m; j < m + size; j++) {
						visited[i][j] = true;
					}
				}
				
//				System.out.println(cnt + "번째 size = " + size);
				int nn = n, nm = m + 1;
				if (m == 9) {
					nn = n + 1;
					nm = 0;
				}
				
				backtracking(nn, nm, cnt + 1);
				paperCnt[size]--;
				
				for (int i = n; i < n + size; i++) {
					for (int j = m; j < m + size; j++) {
						visited[i][j] = false;
					}
				}
//				if (cnt == 0 && size == 3) {
//					print();
//				}
			}
		}
	}
	
	public static void print() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Arrays.toString(visited[i]));
		}
	}
}
