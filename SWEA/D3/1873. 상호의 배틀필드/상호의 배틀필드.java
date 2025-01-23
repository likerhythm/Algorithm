/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */

class Solution
{static int TC;
    static int H;
    static int W;
    static char[][] board;
    static int commandCnt;
    static char[] commands;
    static int tankH;
    static int tankW;
    static char tankDir;
    static int[] dhs = {-1, 0, 1, 0};
    static int[] dws = {0, 1, 0, -1};
    static Map<Character, Integer> dirIndexMapper;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        TC = getInt(br.readLine());
        for (int tc=1; tc<=TC; tc++) {
            setInput();
            for (char command : commands) {
                if (command != 'S') {
                    move(command);
                } else {
                    shoot();
                }
            }
            System.out.print("#" + tc + " ");
            printBoard();
        }
    }

    private static void move(char command) {
        int nextDir = dirIndexMapper.get(command);

        int nh = tankH + dhs[nextDir];
        int nw = tankW + dws[nextDir];
        if (canMove(nh, nw)) {
            board[nh][nw] = getCharDir(nextDir);
            board[tankH][tankW] = '.';
            tankH = nh;
            tankW = nw;
        } else {
            board[tankH][tankW] = getCharDir(nextDir);
        }
        tankDir = getCharDir(nextDir);
    }

    private static void shoot() {
        int intDir = getIntDir(tankDir);
        int dh = dhs[intDir];
        int dw = dws[intDir];
        int add = 1;
        while (true) {
            int nh = tankH + dh * add;
            int nw = tankW + dw * add;
            if (!inRange(nh, nw) || board[nh][nw] == '#') { // 벗어나거나 강철일 경우
                break;
            }
            if (board[nh][nw] == '*') { // 벽돌일 경우
                board[nh][nw] = '.';
                break;
            }
            add++;
        }
    }

    private static void setInput() throws IOException {
        String[] split = br.readLine().split(" ");
        H = getInt(split[0]);
        W = getInt(split[1]);
        board = new char[H][W];
        dirIndexMapper = new HashMap<>();
        dirIndexMapper.put('U', 0);
        dirIndexMapper.put('R', 1);
        dirIndexMapper.put('D', 2);
        dirIndexMapper.put('L', 3);

        for (int i=0; i<H; i++) {
            String input = br.readLine();
            for (int j=0; j<W; j++) {
                char c = input.charAt(j);
                if (c == '^' || c == '>' || c == 'v' || c == '<') {
                    tankH = i;
                    tankW = j;
                    tankDir = c;
                }
                board[i][j] = c;
            }
        }

        commandCnt = getInt(br.readLine());
        commands = br.readLine().toCharArray();
    }

    private static int getInt(String str) {
        return Integer.parseInt(str);
    }

    private static boolean canMove(int h, int w) {
        if (!inRange(h, w) || board[h][w] == '*' || board[h][w] == '#' || board[h][w] == '-') {
            return false;
        }
        return true;
    }

    private static boolean inRange(int h, int w) {
        return 0 <= h && h < H && 0 <= w && w < W;
    }

    private static char getCharDir(int dir) {
        if (dir == 0) return '^';
        if (dir == 1) return '>';
        if (dir == 2) return 'v';
        else return '<';
    }

    private static int getIntDir(char dir) {
        if (dir == '^') return 0;
        if (dir == '>') return 1;
        if (dir == 'v') return 2;
        else return 3;
    }

    private static void printBoard() {
        for (int i=0; i<H; i++) {
            for (int j=0; j<W; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}