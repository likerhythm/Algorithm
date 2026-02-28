class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long minRow = x;
        long maxRow = x;
        long minCol = y;
        long maxCol = y;

        // 쿼리를 역순으로 탐색
        for (int i = queries.length - 1; i >= 0; i--) {
            int command = queries[i][0];
            long dx = queries[i][1];

            if (command == 0) { 
                // 원본: 왼쪽 -> 역방향: 오른쪽
                if (minCol != 0) minCol += dx;
                maxCol = Math.min((long)m - 1, maxCol + dx);
                if (minCol > m - 1) return 0; // 범위를 완전히 벗어난 경우
            } else if (command == 1) { 
                // 원본: 오른쪽 -> 역방향: 왼쪽
                if (maxCol != m - 1) maxCol -= dx;
                minCol = Math.max(0L, minCol - dx);
                if (maxCol < 0) return 0;
            } else if (command == 2) { 
                // 원본: 위쪽 -> 역방향: 아래쪽
                if (minRow != 0) minRow += dx;
                maxRow = Math.min((long)n - 1, maxRow + dx);
                if (minRow > n - 1) return 0;
            } else if (command == 3) { 
                // 원본: 아래쪽 -> 역방향: 위쪽
                if (maxRow != n - 1) maxRow -= dx;
                minRow = Math.max(0L, minRow - dx);
                if (maxRow < 0) return 0;
            }
        }

        // 최종적으로 남은 가능한 시작점의 개수: 넓이
        return (maxRow - minRow + 1) * (maxCol - minCol + 1);
    }
}