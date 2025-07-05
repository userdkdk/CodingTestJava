import java.util.*;
import java.io.*;

public class Main {
    static long n, m; static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Long.parseLong(st.nextToken());
        m = Long.parseLong(st.nextToken());
        ans = Integer.MAX_VALUE;
        check(n,1);
        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }
    static void check(long a, int c) {
        if (a>m) return;
        else if (a==m) {
            ans = Math.min(ans,c);
        } else {
            check(2*a,c+1);
            check(10*a+1,c+1);
        }
    }
}
