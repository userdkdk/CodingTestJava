import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

class Main
{
    static int n, ans; static int[] ori_arr, tar_arr, num;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        int[] ans_arr = new int[n+1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        ans_arr[1] = arr[1];
        if (n>=2) {
            ans_arr[2] = arr[2] + arr[1];
        }
        for (int i=3;i<=n;i++) {
            ans_arr[i] = Math.max(ans_arr[i-3]+arr[i-1]+arr[i], ans_arr[i-2]+arr[i]);
        }
        System.out.println(ans_arr[n]);

    }
}