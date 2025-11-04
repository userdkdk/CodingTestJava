import java.io.*;
import java.util.*;

public class Main {
    static long min, max; static int range; static boolean[] prime_check, ans_check;
    static ArrayList<Long> prime_list; static int diff;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        min = Long.parseLong(st.nextToken());
        max = Long.parseLong(st.nextToken());
        range = (int) Math.sqrt(max)+1;
        prime_check = new boolean[range];
        prime_list = new ArrayList<>();
        getPrime();
        diff = Math.toIntExact(max - min+1);
        ans_check = new boolean[diff];
        ansCheck();
        int ans = 0;
        for (int i=0;i<diff;i++) {
            if (!ans_check[i]) ans++;
        }
        System.out.println(ans);
    }
    static void ansCheck() {
        for (long num : prime_list) {
            if (num>max) break;
            long q = min%num==0 ? min/num : min/num+1;
            long loc = num*q-min;
            if (loc>diff) continue;
            int tmp = (int) loc;
            while (tmp<diff) {
                ans_check[tmp] = true;
                if (num>diff) break;
                tmp += (int) num;
            }
        }
    }
    static void getPrime() {
        for (int i=2;i<range;i++) {
            if (prime_check[i]) continue;
            prime_list.add((long) i*i);
            for (int j=i;j<range;j+=i) prime_check[j] = true;
        }
    }
}
