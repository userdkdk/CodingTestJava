import java.sql.SQLOutput;
import java.util.*;
import java.io.*;

public class Main {
    static int n; static int p = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)) ;
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        long ans = 0;
        for (int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            long b = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());

            long tmp = pow(b,p-2);
            ans = (ans + a*tmp)%p;
        }

        System.out.println(ans);
    }
    static long pow (long num, int exp) {
        long res = 1;
        while (exp > 0) {
            if (exp % 2 ==1) {
                res = (res*num)%p;
            }
            num = num*num%p;
            exp /=2;
        }
        return res;
    }
}
