import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;


public class Main {
    static int[] arr; static int n, k;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n];
        long ans = 0;
        for (int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(br.readLine());
            ans = Math.max(ans,arr[i]);
        }
        long min = 0;
        long max = ans;
        while (true) {
            if (Math.abs(max-min)<=1) {
                if (count_n(max)<k) {
                    ans = min;
                } else {
                    ans = max;
                }
                break;
            }

            ans = ((min + max)/2);
            long cnt = count_n(ans);
            if (cnt<k) {
                max = ans-1;
            } else {
                min = ans;
            }
        }
        System.out.println(ans);

    }
    static long count_n(long num) {
        long c = 0;
        for (int i=0;i<n;i++) {
            c += arr[i]/num;
        }
        return c;
    }
}