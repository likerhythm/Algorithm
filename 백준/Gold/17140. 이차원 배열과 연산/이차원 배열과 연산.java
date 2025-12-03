import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    static int r, c, k;
    static int[][] tmpArr;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        tmpArr = new int[3][3];
        for (int i = 0; i < 3; i++) {
            tmpArr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int time = 0;
        while (time <= 100) {
            int rSize = tmpArr.length;
            int cSize = tmpArr[0].length;
            arr = new int[Math.max(rSize, cSize)][Math.max(rSize, cSize)];
            for (int i = 0; i < rSize; i++) {
                for (int j = 0; j < cSize; j++) {
                    arr[i][j] = tmpArr[i][j];
                }
            }
//            printArr(tmpArr);
            if (r - 1 < rSize && c - 1 < cSize && arr[r - 1][c - 1] == k) {
                break;
            }

            if (rSize >= cSize) {
                commandR();
            } else {
                commandC();
            }

            time++;
        }

        if (time > 100) {
            System.out.println(-1);
            return;
        }
        System.out.println(time);
    }

    private static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void commandR() {
        List<int[]> tmps = new ArrayList<>();
        int maxLength = 0;
        for (int i = 0; i < arr.length; i++) {
            int[] tmp = calc(arr[i]);
            maxLength = Math.max(maxLength, tmp.length);
            tmps.add(tmp);
        }

        tmpArr = new int[arr.length][maxLength];
        for (int i = 0; i < arr.length; i++) {
            int[] a = tmps.get(i);
            for (int j = 0; j < a.length; j++) {
                tmpArr[i][j] = a[j];
            }
        }
    }

    private static int[] calc(int[] a) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int value : a) {
            if (value == 0) continue;
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
            }
        }

        Map<Integer, Integer> sortedMap = map.entrySet().stream()
                .sorted(Entry.<Integer, Integer>comparingByValue()
                        .thenComparing(Entry::getKey)
                )
                .collect(Collectors.toMap(
                        Entry::getKey,
                        Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        int[] result = new int[map.size() * 2];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            result[idx++] = entry.getKey();
            result[idx++] = entry.getValue();
            if (idx == 100) break;
        }
        return result;
    }

    private static void commandC() {
        List<int[]> tmps = new ArrayList<>();
        int maxLength = 0;
        for (int j = 0; j < arr[0].length; j++) {
            int[] a = new int[arr[0].length];
            for (int i = 0; i < arr.length; i++) {
                a[i] = arr[i][j];
            }
            int[] tmp = calc(a);
            maxLength = Math.max(maxLength, tmp.length);
            tmps.add(tmp);
        }

        tmpArr = new int[maxLength][arr[0].length];
        for (int j = 0; j < arr[0].length; j++) {
            int[] a = tmps.get(j);
            for (int i = 0; i < a.length; i++) {
                tmpArr[i][j] = a[i];
            }
        }
    }
}
