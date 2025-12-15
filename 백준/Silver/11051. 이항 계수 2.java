import java.io.*;
import java.util.*;

public class Main{
    
	static Integer num[][];
	static int N,K;
    
	public static void main(String[] args) throws IOException{
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	num = new Integer[N+1][N+1];
    	int result = binomialCoefficient(N,K);

    	bw.write(result + "\n");
    	bw.flush();
    	bw.close();
    	br.close();
	}
    
	public static int binomialCoefficient(int N, int K) {
		if(K==0 || N==K)
			return 1;
		if(num[N][K]==null) 
			num[N][K] = (binomialCoefficient(N-1, K-1) + binomialCoefficient(N-1, K))%10007;
		
		return num[N][K];
	}
}
