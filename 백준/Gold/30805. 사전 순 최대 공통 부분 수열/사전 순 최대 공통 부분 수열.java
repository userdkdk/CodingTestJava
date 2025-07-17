import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class Main {
    static int n, m; static int[] arr1, arr2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr1 = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<n;i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        arr2 = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<m;i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }
        int[] ans_arr = new int[n];
        int idx = 0;
        int arr1_idx = 0;
        int arr2_idx = 0;

        while (arr1_idx<n && arr2_idx<m) {
            int max = 0;
            int loc1 = 0;
            int loc2 = 0;
            for (int i=n-1;i>=arr1_idx;i--) {
                for (int j=m-1;j>=arr2_idx;j--) {
                    if (arr1[i]==arr2[j] && arr1[i]>=max) {
                        loc1 = i;
                        loc2 = j;
                        max = arr1[i];
                    }
                }
            }
            if (max==0) {
                break;
            } else {
                ans_arr[idx++] = max;
                arr1_idx = loc1+1;
                arr2_idx = loc2+1;
            }
        }
        System.out.println(idx);
        if (idx!=0) {
            for (int i=0;i<idx;i++) {
                System.out.print(ans_arr[i]+" ");
            }
        }
    }
}
