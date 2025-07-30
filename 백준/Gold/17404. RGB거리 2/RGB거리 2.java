import java.util.*;
import java.io.*;

public class Main {
    static int N; static int[][] arr; static int[][] ans_arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }
        int ans = Integer.MAX_VALUE;
        for (int sta=0;sta<3;sta++) {
            ans_arr = new int[N][3];
            Arrays.fill(ans_arr[0],1000000);
            ans_arr[0][sta] = arr[0][sta];
            for (int i=1;i<N;i++) {
                ans_arr[i][0] = Math.min(ans_arr[i-1][1],ans_arr[i-1][2]) + arr[i][0];
                ans_arr[i][1] = Math.min(ans_arr[i-1][0],ans_arr[i-1][2]) + arr[i][1];
                ans_arr[i][2] = Math.min(ans_arr[i-1][0],ans_arr[i-1][1]) + arr[i][2];
            }
            ans_arr[N-1][sta] +=1000000;
            for (int i=0;i<3;i++) {
                ans = Math.min(ans_arr[N-1][i],ans);
            }
        }
        System.out.println(ans);

    }
}
