import java.util.*;
import java.io.*;

public class Main {
    static int N; static long[] arr; static int max = 1000020;
    static int div = 1000000009;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        arr = new long[max];
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 4;
        for (int i=4;i<max;i++) {
            arr[i] = (arr[i-1]+arr[i-2]+arr[i-3])%div;
        }
        for (int tc=0;tc<T;tc++) {
            N = Integer.parseInt(br.readLine());
            System.out.println(arr[N]);
        }
    }
}
