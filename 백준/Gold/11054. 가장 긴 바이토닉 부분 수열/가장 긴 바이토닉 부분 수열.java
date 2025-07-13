import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class Main {
    static int n, max; static int[] arr, inc_arr, dec_arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        inc_arr = new int[n];
        dec_arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(inc_arr,1);
        Arrays.fill(dec_arr,1);
        for (int i=0;i<n;i++) {
            for (int j=0;j<i;j++) {
                if (arr[i]>arr[j]) inc_arr[i] = Math.max(inc_arr[i],inc_arr[j]+1);
            }
        }
        dec_arr[n-1] = 1;
        for (int i=n-1;i>=0;i--) {
            for (int j=n-1;j>i;j--) {
                if (arr[i]>arr[j]) dec_arr[i] = Math.max(dec_arr[i],dec_arr[j]+1);
            }
        }
        for (int i=0;i<n;i++) {
            max = Math.max(max,inc_arr[i]+dec_arr[i]-1);
        }
        System.out.println(max);
    }
}
