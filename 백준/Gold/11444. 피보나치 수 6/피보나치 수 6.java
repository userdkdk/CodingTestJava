import java.util.*;
import java.io.*;

public class Main {
    static long n; static long number = 1000000007; static HashMap<Long, Long> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Long.parseLong(br.readLine());
        map = new HashMap<>();
        map.put(0L,0L);
        map.put(1L,1L);
        map.put(2L,1L);
        map.put(3L,2L);
        System.out.println(pow(n));
    }
    static long pow(long exp) {
        if (map.get(exp)!=null) return map.get(exp);
        long result = 0L;
        if (exp%2==0) {
            long a = pow(exp/2);
            long b = pow(exp/2-1);
            long c = pow(exp/2+1);
            result = (a*(b+c))%number;
        } else {
            long a = pow(exp/2+1);
            long b = pow(exp/2+2);
            long c = pow(exp/2-1);
            long d = pow(exp/2);
            result = ((a*b)%number-(c*d)%number)%number;
        }
        if (result<0) result+=number;
        map.put(exp,result);
        return result;
    }
}
