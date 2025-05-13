import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

class Main
{
    static int[][] arr; static int[][] ans_arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        arr = new int[n+1][3];
        ans_arr = new int[n+1][3];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }
        ans_arr[1][0] = arr[1][0];
        ans_arr[1][1] = arr[1][1];
        ans_arr[1][2] = arr[1][2];
        for (int i=2;i<=n;i++) {
            ans_arr[i][0] = Math.min(ans_arr[i-1][1], ans_arr[i-1][2]) + arr[i][0];
            ans_arr[i][1] = Math.min(ans_arr[i-1][0],ans_arr[i-1][2]) + arr[i][1];
            ans_arr[i][2] = Math.min(ans_arr[i-1][0], ans_arr[i-1][1]) + arr[i][2];
        }
        System.out.println(Math.min(ans_arr[n][0],Math.min(ans_arr[n][1],ans_arr[n][2])));

    }
}