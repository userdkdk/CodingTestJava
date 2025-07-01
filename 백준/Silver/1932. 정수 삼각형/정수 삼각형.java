import java.util.*;
import java.io.*;

public class Main {
    static int n; static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[501][501];

        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<=i;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i=1;i<n;i++) {
            arr[i][0] += arr[i-1][0];
            for (int j=1;j<i;j++) {
                arr[i][j] += Math.max(arr[i-1][j-1],arr[i-1][j]);
            }
            arr[i][i] += arr[i-1][i-1];
        }
        int max = 0;
        for (int i=0;i<n;i++) {
            max = Math.max(arr[n-1][i],max);
        }
        System.out.println(max);
    }
}
