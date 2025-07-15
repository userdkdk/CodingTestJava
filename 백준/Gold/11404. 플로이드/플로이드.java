import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class Main {
    static int n, m; static int[][] arr, dist_arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i=0;i<n;i++) {
            Arrays.fill(arr[i],10000000);
        }
        for (int i=0;i<n;i++) {
            arr[i][i] = 0;
        }

        for (int i=0;i<m;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken());
            arr[a][b] = Math.min(arr[a][b],c);
        }

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                for (int k=0;k<n;k++) {
                    arr[j][k] = Math.min(arr[j][k],arr[j][i] + arr[i][k]);
                }
            }
        }

        for (int i=0;i<n;i++) {
            for (int j=0;j<n;j++) {
                if (arr[i][j]==10000000) System.out.print(0+" ");
                else System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }

    }
}
