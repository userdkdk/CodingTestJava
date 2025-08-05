import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class Main {
    static int N; static long[] arr; static long min; static int[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);
        ans = new int[3];
        min = Long.MAX_VALUE;
        out:
        for (int i=0;i<N-2;i++) {
            for (int j=i+1;j<N-1;j++) {
                findLow(j+1,N-1,arr[i]+arr[j], i, j);
                if (min==0) break out;
            }
        }
        System.out.println(arr[ans[0]]+" "+arr[ans[1]]+" "+arr[ans[2]]);
    }
    static void findLow(int sta, int las, long num, int a, int b) {
        if (sta>=las) {
            if (Math.abs(arr[sta]+num) < min) {
                min = Math.abs(arr[sta]+num);
                ans[0] = a;
                ans[1] = b;
                ans[2] = sta;
            }
            return;
        }
        int mid = (sta + las)/2;

        long value = arr[mid] + num;
        if (Math.abs(value) < min) {
            min = Math.abs(value);
            ans[0] = a;
            ans[1] = b;
            ans[2] = mid;
        }

        if (value > 0) {
            findLow(sta,mid,num, a, b);
        } else if (value == 0) {
            min = 0;
            ans[0] = a;
            ans[1] = b;
            ans[2] = mid;
            return;
        } else {
            findLow(mid+1,las,num, a, b);
        }
    }
}
