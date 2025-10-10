import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K; static int[][] arr; static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        for (int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            arr[a][b] = 1;
            arr[a][b+1] = -1;
        }
        flag = false;
        int ans = -1;
        for (int i=0;i<=3;i++) {
            bfs(0,0,0,i);
            if (flag) {
                ans = i;
                break;
            }
        }
        System.out.println(ans);
    }
    static void bfs(int a, int b, int idx, int size) {
        if (idx==size) {
            if (checkArr()) {
                flag = true;
            }
            return;
        }
        out:
        for (int i=a;i<N;i++) {
            int staj = i==a ? b:0;
            for (int j=staj;j<M-1;j++) {
                if (flag) break out;
                if (arr[i][j]==0 && arr[i][j+1]==0) {
                    arr[i][j] = 1;
                    arr[i][j+1] = -1;
                    int new_a = i + (j+1)/(M-1);
                    int new_b = (j+1)%(M-1);
                    bfs(new_a, new_b, idx+1,size);
                    arr[i][j] = 0;
                    arr[i][j+1] = 0;
                }
            }
        }

    }
    static boolean checkArr() {
        for (int j=0;j<M;j++) {
            int b = j;
            for (int i=0;i<N;i++) {
                b += arr[i][b];
            }
            if (b!=j) return false;
        }
        return true;
    }
}
