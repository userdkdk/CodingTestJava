import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class Main {
    static int n, max; static int[] inc_arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        inc_arr = new int[n];

        st = new StringTokenizer(br.readLine());
        int f = Integer.parseInt(st.nextToken());
        inc_arr[0] = f;

        int inc_loc = 0;
        for (int i=1;i<n;i++) {
            int now = Integer.parseInt(st.nextToken());
            int tmp_inc_loc = find_inc_loc(now,0,inc_loc);
            if (inc_arr[tmp_inc_loc]<now) {
                inc_loc++;
                inc_arr[inc_loc] = now;
            } else {
                inc_arr[tmp_inc_loc] = now;
            }
        }
        System.out.println(inc_loc+1);
    }
    static int find_inc_loc(int now, int min, int max) {
        if (min >= max) {
            return min;
        }
        int mid = (min + max)/2;
        if (inc_arr[mid]==now) {
            return mid;
        } else if (inc_arr[mid]>now) {
            return find_inc_loc(now,min,mid);
        } else {
            return find_inc_loc(now,mid+1,max);
        }
    }
}
