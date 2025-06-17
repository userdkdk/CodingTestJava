import java.util.*;
import java.io.*;

public class Main {
    static int n, ans; static int[][][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st ;
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n][6];
        ans = 0;

        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<n;j++) {
                arr[i][j][0] = Integer.parseInt(st.nextToken());
            }
        }
        bfs(0);
        System.out.println(ans);

//        moving(0,1);
//        for (int i=0;i<n;i++) {
//            for (int j=0;j<n;j++) {
//                System.out.print(arr[i][j][1]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();

//        int[] line = {0,8,0,4,0,4};
//        int[] tmp_line = mergedLine(line,6);
//        System.out.println(Arrays.toString(tmp_line));
    }
    static void bfs(int depth) {
        if (depth==5) {
            for (int i=0;i<n;i++) {
                for (int j=0;j<n;j++) {
                    ans = Math.max(ans,arr[i][j][5]);
                }
            }

            return;
        }
        for (int d=0;d<4;d++) {
            clearArr(depth);
            moving(depth, d);

            bfs(depth+1);

        }
    }
    //
    static void moving(int depth, int d) {
        for (int i=0;i<n;i++) {
            // 라인추출
            int[] line = extractLine(i,d,depth);
            // 병합
            int[] merged = mergedLine(line,n);
            // 입력
            insertLine(merged, i, d, depth);
        }
    }
    static int[] extractLine(int idx, int d, int depth) {
        int[] line = new int[n];
        for (int i=0;i<n;i++) {
            switch (d) {
                case 0:
                    line[i] = arr[i][idx][depth]; // up
                    break;
                case 1:
                    line[n-i-1] = arr[i][idx][depth]; // down
                    break;
                case 2:
                    line[i] = arr[idx][i][depth]; // left
                    break;
                case 3:
                    line[n-i-1] = arr[idx][i][depth]; // right
                    break;
            }
        }
        return line;
    }
    static int[] mergedLine(int[] line, int nn) {
        int[] tmp = new int[nn];

        Deque<Integer> dq = new ArrayDeque<>();
        for (int i=0;i<nn;i++) {
            if (line[i]==0) continue;
            dq.addLast(line[i]);
        }
        int idx = 0;
        while (!dq.isEmpty()) {
            int now = dq.pollFirst();
            if (dq.isEmpty()) {
                tmp[idx] = now;
                break;
            }
            int next = dq.pollFirst();
            if (now==next) {
                tmp[idx++] = now*2;
            } else {
                tmp[idx++] = now;
                dq.addFirst(next);
            }
        }

        return tmp;
    }

    static void insertLine(int[] line, int idx, int d, int depth) {
        for (int i=0;i<n;i++) {
            switch (d) {
                case 0:
                    arr[i][idx][depth+1] = line[i]; // up
                    break;
                case 1:
                    arr[i][idx][depth+1] = line[n-i-1]; // down
                    break;
                case 2:
                    arr[idx][i][depth+1] = line[i]; // left
                    break;
                case 3:
                    arr[idx][i][depth+1] = line[n-i-1]; // right
            }
        }
    }

    static void clearArr(int depth) {
        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                arr[i][j][depth+1] = 0;
            }
        }
    }
}
