import java.io.*;
import java.util.*;

public class Main {
    static int N, K, S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        boolean[][] canGo = new boolean[N+1][N+1];
        for (int i=0;i<K;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            canGo[a][b] = true;
        }

        for (int k=1;k<=N;k++) {
            for (int i=1;i<=N;i++) {
                for (int j=1;j<=N;j++) {
                    if (canGo[i][k] && canGo[k][j]) {
                        canGo[i][j] = true;
                    }
                }
            }
        }

        S = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<S;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (canGo[a][b]) {
                sb.append("-1").append("\n");
            } else if (canGo[b][a]) {
                sb.append("1").append("\n");
            } else {
                sb.append("0").append("\n");
            }
        }
        System.out.println(sb);
    }
}
