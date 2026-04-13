import java.util.*;
import java.io.*;

public class Main{

  static class Score implements Comparable<Score> {
    int doc, interview;

    Score(int doc, int interview) {
      this.doc = doc;
      this.interview = interview;
    }

    @Override
    public int compareTo(Score other) {
      return Integer.compare(this.doc, other.doc);
    }

    @Override
    public String toString() {
      return "[" + doc + ", " + interview + "]";
    }
  }
  
  public static void main(String args[]) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());

    while (T-- > 0) {
      int N = Integer.parseInt(br.readLine());
      Score[] scores = new Score[N];
      for (int i = 0; i < N; i++) {
        StringTokenizer st = new StringTokenizer(br.readLine());
        scores[i] = new Score(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      }

      Arrays.sort(scores);
      // System.out.println(Arrays.toString(scores));

      int answer = 1;
      Score now = scores[0];
      int min = now.interview;
      for (int i = 1; i < N; i++) {
        Score next = scores[i];
        if (min > next.interview) {
          answer++;
          min = next.interview;
        }
        now = next;
      }
      
      System.out.println(answer);
    }
  }
}
