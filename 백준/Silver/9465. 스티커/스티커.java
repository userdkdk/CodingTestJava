import java.util.*;
import java.io.*;

public class Main {
    static int n; static int[][] arr, ans_arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc=1;tc<=T;tc++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n][2];
            ans_arr = new int[n][2];
            st = new StringTokenizer(br.readLine());
            for (int i=0;i<n;i++) {
                arr[i][0] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i=0;i<n;i++) {
                arr[i][1] = Integer.parseInt(st.nextToken());
            }
            ans_arr[0][0] = arr[0][0];
            ans_arr[0][1] = arr[0][1];

            for (int i=1;i<n;i++) {
                ans_arr[i][0] = Math.max(ans_arr[i-1][0],ans_arr[i-1][1]+arr[i][0]);
                ans_arr[i][1] = Math.max(ans_arr[i-1][1],ans_arr[i-1][0]+arr[i][1]);
            }
            System.out.println(Math.max(ans_arr[n-1][0],ans_arr[n-1][1]));

        }
    }
}
