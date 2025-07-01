import java.util.*;
import java.io.*;

public class Main {
    static int n; static int[] arr, ans_arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        ans_arr = new int[n];
        Arrays.fill(ans_arr,1);

        st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int max = 1;
        for (int i=1;i<n;i++) {
            for (int j=0;j<i;j++) {
                if (arr[i]>arr[j]) {
                    ans_arr[i] = Math.max(ans_arr[i],ans_arr[j]+1);
                }
            }
            max = Math.max(max,ans_arr[i]);
        }
        System.out.println(max);
    }
}
