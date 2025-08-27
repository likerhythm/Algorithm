import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static class Painting implements Comparable<Painting> {
		int height;
		int cost;

		public Painting(int height, int cost) {
			super();
			this.height = height;
			this.cost = cost;
		}

		@Override
		public int compareTo(Painting o) {
			return this.height - o.height;
		}

		@Override
		public String toString() {
			return "Painting [height=" + height + ", cost=" + cost + "]";
		}

	}

	static Painting[] paintingArray;
	static int[] limit, DP;
	static int N, S;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		paintingArray = new Painting[N];
		DP = new int[N];
		limit = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			paintingArray[i] = new Painting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Arrays.sort(paintingArray);

		DP[0] = paintingArray[0].cost;
		
		for (int i = 1; i < N; i++) {
			int findMaxHightIndex = bSearch(i);
			if(findMaxHightIndex > -1) {
				//탐색 시
				DP[i] = Math.max(DP[findMaxHightIndex] + paintingArray[i].cost, DP[i - 1]);
			} else {
				//탐색하지 못하면
				DP[i] = Math.max(DP[i-1], paintingArray[i].cost);
			}
			
		}

		System.out.println(DP[N - 1]);

	}

	static int bSearch(int curPic) {
		int mid = 0, left = 0, right = curPic;
		while (left <= right) {
			mid = (left + right) / 2;
			int diff = paintingArray[curPic].height - paintingArray[mid].height;
			if (diff >= S)
				left = mid + 1;
			else
				right = mid - 1;
		}
		return right;
	}
}