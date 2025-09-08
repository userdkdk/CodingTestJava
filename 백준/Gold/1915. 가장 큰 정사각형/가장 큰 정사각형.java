import java.util.*;
import java.io.*;

public class Main {
    static int N, M; static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i=0;i<N;i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j=0;j<M;j++) {
                arr[i][j] = tmp[j]-'0';
            }
        }
        int max = 0;
        for (int i=0;i<N;i++) {
            max = Math.max(max,arr[i][0]);
        }
        for (int i=0;i<M;i++) {
            max = Math.max(max,arr[0][i]);
        }
        for (int i=1;i<N;i++) {
            for (int j=1;j<M;j++) {
                if (arr[i][j]==1) {
                    arr[i][j] = Math.min(arr[i-1][j-1],
                            Math.min(arr[i-1][j],arr[i][j-1])) + 1;
                    max = Math.max(max,arr[i][j]);
                }
            }
        }
        System.out.println(max*max);
    }
}
