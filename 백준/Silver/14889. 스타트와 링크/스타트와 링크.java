import java.util.*;
import java.io.*;

public class Main {
    static int N, min; static boolean[] team1; static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        team1 = new boolean[N];
        arr = new int[N][N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        min = Integer.MAX_VALUE;
        brute(0,0);
        System.out.println(min);
    }
    static int cal() {
        int t1 = 0;
        int t2 = 0;
        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                if (team1[i]==team1[j]) {
                    if (team1[i]) t1 += arr[i][j] + arr[j][i];
                    else t2 += arr[i][j] + arr[j][i];
                }
            }
        }
        return Math.abs(t1/2-t2/2);
    }
    static void brute(int idx, int c) {
        if (c==N/2) {
            int dif = cal();
            min = Math.min(min,dif);
            return;
        }
        for (int i=idx;i<=N/2+c;i++) {
            team1[i] = true;
            brute(i+1, c+1);
            team1[i] = false;
        }
    }
}
