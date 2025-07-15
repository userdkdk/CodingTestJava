import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class Main {
    static int n, m, r; static int[] items; static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        items = new int[n+1];
        arr = new int[n+1][n+1];

        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=n;i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for (int i=1;i<=n;i++) {
            Arrays.fill(arr[i],1000000);
        }
        for (int i=1;i<=n;i++) {
            arr[i][i] = 0;
        }

        for (int i=0;i<r;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            arr[a][b] = l;
            arr[b][a] = l;
        }

        for (int k=1;k<=n;k++) {
            for (int i=1;i<=n;i++) {
                for (int j=1;j<=n;j++) {
                    arr[i][j] = Math.min(arr[i][j],arr[i][k]+arr[k][j]);
                }
            }
        }

        int ans = 0;
        for (int i=1;i<=n;i++) {
            int tmp = 0;
            for (int j=1;j<=n;j++) {
                if (arr[i][j]<=m) tmp += items[j];
            }
            ans = Math.max(ans,tmp);
        }
        System.out.println(ans);
    }
}
